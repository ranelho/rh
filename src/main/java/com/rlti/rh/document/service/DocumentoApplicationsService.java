package com.rlti.rh.document.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.rlti.rh.document.api.response.FileUploadResponse;
import com.rlti.rh.document.domain.FileReference;
import com.rlti.rh.document.repository.DocumentoRepository;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import com.rlti.rh.handler.APIException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentoApplicationsService implements DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final MatriculaRepository matriculaRepository;
    private AmazonS3 s3client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.s3.accessKey}")
    private String accessKey;

    @Value("${aws.s3.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initialize() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    @Override
    @Transactional
    public FileUploadResponse uploadFile(String numeroMatricula, MultipartFile file) {
        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = dateTimeFormatter.format(LocalDate.now());
        String filePath = "";
        try {
            // Busca a matrícula
            Matricula matricula = matriculaRepository.findByNumeroMatricula(numeroMatricula);
            if (matricula == null) {
                throw APIException.build(HttpStatus.NOT_FOUND, "Matrícula não encontrada");
            }

            // Gera o caminho do arquivo
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf('.'))
                    : "";
            filePath = String.format("%s/%s-%s%s", todayDate, UUID.randomUUID(), System.currentTimeMillis(), fileExtension);

            // Configura metadados do arquivo
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            s3client.putObject(bucketName, filePath, file.getInputStream(), objectMetadata);

            // Gera a URL do arquivo
            String fileUrl = String.format("https://%s.s3.amazonaws.com/%s", bucketName, filePath);

            // Salva a referência ao arquivo no banco de dados
            FileReference fileReference = new FileReference(matricula, filePath, fileUrl);
            documentoRepository.save(fileReference);

            // Configura a resposta
            fileUploadResponse.setFilePath(filePath);
            fileUploadResponse.setFileUrl(fileUrl);
            fileUploadResponse.setDateTime(LocalDateTime.now());

        } catch (IOException e) {
            log.error("Erro ao acessar o arquivo: ", e);
            throw APIException.build(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao acessar o arquivo");
        } catch (Exception e) {
            log.error("Erro ao fazer upload do arquivo: ", e);
            throw APIException.build(HttpStatus.BAD_REQUEST, "Erro ao fazer upload do arquivo");
        }

        return fileUploadResponse;
    }

}


