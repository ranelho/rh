package com.rlti.rh.document.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.rlti.rh.document.api.response.FileResponse;
import com.rlti.rh.document.api.response.FileUploadResponse;
import com.rlti.rh.document.domain.FileReference;
import com.rlti.rh.document.repository.DocumentoRepository;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import com.rlti.rh.handler.APIException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentoApplicationsService implements DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final MatriculaRepository matriculaRepository;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    private final AmazonS3 s3Client;

    @Override
    @Transactional
    public FileUploadResponse uploadFile(String numeroMatricula, MultipartFile file) {
        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = dateTimeFormatter.format(LocalDate.now());
        String filePath = "";
        try {
            Matricula matricula = matriculaRepository.findByNumeroMatricula(numeroMatricula);
            if (matricula == null) {
                throw APIException.build(HttpStatus.NOT_FOUND, "Matrícula não encontrada");
            }

            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf('.'))
                    : "";
            filePath = String.format("%s/%s-%s%s", todayDate, UUID.randomUUID(), System.currentTimeMillis(), fileExtension);

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            s3Client.putObject(bucketName, filePath, file.getInputStream(), objectMetadata);

            String fileUrl = String.format("https://%s.s3.amazonaws.com/%s", bucketName, filePath);

            FileReference fileReference = new FileReference(matricula, filePath, fileUrl);
            documentoRepository.save(fileReference);

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

    @Override
    @Transactional
    public byte[] downloadFile(String filePath) {
        try {
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, filePath);
            S3Object s3Object = s3Client.getObject(getObjectRequest);

            try (InputStream inputStream = s3Object.getObjectContent();
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                return byteArrayOutputStream.toByteArray();
            }
        } catch (IOException e) {
            log.error("Erro ao baixar o arquivo: ", e);
            throw APIException.build(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao baixar o arquivo");
        }
    }

    @Override
    public List<FileResponse> getAllDocumentsByMatricula(String numeroMatricula) {
        Matricula matricula = matriculaRepository.findByNumeroMatricula(numeroMatricula);
        List<FileReference> fileReferences = new ArrayList<>();
        if (matricula != null) {
            fileReferences = documentoRepository.findByMatricula(matricula);
        }
        return FileResponse.convert(fileReferences);
    }


}


