package com.rlti.rh.document.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.rlti.rh.document.api.response.FileUploadResponse;
import com.rlti.rh.document.repository.DocumentoRepository;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import com.rlti.rh.handler.APIException;
import jakarta.annotation.PostConstruct;
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


@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentoApplicationsService implements DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final MatriculaRepository matriculaRepository;
    /*  private final S3Service s3Service;

      @Value("${aws.s3.bucket-name}")
      private String bucketName;

      @Override
      @Transactional
      public String uploadFile(String numeroMatricula, MultipartFile file) {
          try {
              // Busca a matrícula
              Matricula matricula = matriculaRepository.findByNumeroMatricula(numeroMatricula);
              if (matricula == null) {
                  throw APIException.build(HttpStatus.NOT_FOUND, "Matrícula não encontrada");
              }

              // Gera a chave (key) automaticamente
              String originalFilename = file.getOriginalFilename();
              String fileExtension = originalFilename != null && originalFilename.contains(".")
                      ? originalFilename.substring(originalFilename.lastIndexOf('.'))
                      : "";
              String key = String.format("%s/%s-%s%s", numeroMatricula, UUID.randomUUID(), System.currentTimeMillis(), fileExtension);

              // Faz o upload para o S3
              s3Service.uploadToS3(key, file.getInputStream(), file.getSize());

              // Gera a URL do arquivo
              String fileUrl = String.format("https://%s.s3.amazonaws.com/%s", bucketName, key);

              // Salva a referência ao arquivo no banco de dados
              FileReference fileReference = new FileReference(matricula, key, fileUrl);
              documentoRepository.save(fileReference);

              return fileUrl;

          } catch (IOException e) {
              log.error("Erro ao acessar o arquivo: ", e);
              throw APIException.build(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao acessar o arquivo");
          } catch (Exception e) {
              log.error("Erro ao fazer upload do arquivo: ", e);
              throw APIException.build(HttpStatus.BAD_REQUEST, "Erro ao fazer upload do arquivo");
          }
      }
  */
    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.s3.accessKey}")
    private String accessKey;

    @Value("${aws.s3.secretKey}")
    private String secretKey;

    private AmazonS3 s3Client;

    @PostConstruct
    private void initialize() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Override
    public FileUploadResponse uploadFile(String numeroMatricula, MultipartFile multipartFile) {
        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = dateTimeFormatter.format(LocalDate.now());
        String filePath = "";
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getSize());
            filePath = todayDate + "/" + multipartFile.getOriginalFilename();
            s3Client.putObject(bucketName, filePath, multipartFile.getInputStream(), objectMetadata);
            fileUploadResponse.setFilePath(filePath);
            fileUploadResponse.setDateTime(LocalDateTime.now());

            Matricula matricula = matriculaRepository.findByNumeroMatricula(numeroMatricula);
            if (matricula == null) {
                throw APIException.build(HttpStatus.NOT_FOUND, "Matrícula não encontrada");
            }
        } catch (IOException e) {
            log.error("Error occurred ==> {}", e.getMessage());
            throw APIException.build(HttpStatus.BAD_REQUEST, "Error occurred in file upload ==> " + e.getMessage());
        }
        return fileUploadResponse;
    }
}


