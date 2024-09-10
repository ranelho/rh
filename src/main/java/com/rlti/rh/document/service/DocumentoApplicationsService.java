package com.rlti.rh.document.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.rlti.rh.document.api.request.DocumentoTypeRequest;
import com.rlti.rh.document.api.response.DocumentoTypeResponse;
import com.rlti.rh.document.api.response.FileResponse;
import com.rlti.rh.document.api.response.FileUploadResponse;
import com.rlti.rh.document.domain.DocumentType;
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
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentoApplicationsService implements DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final MatriculaRepository matriculaRepository;
    private final AmazonS3 s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Override
    @Transactional
    public FileUploadResponse uploadFile(String numeroMatricula, String descricao, MultipartFile file) {
        FileUploadResponse fileUploadResponse;
        String filePath;
        try {
            Matricula matricula = matriculaRepository.findByNumeroMatricula(numeroMatricula);
            filePath = getFilePath("F" + numeroMatricula, file);
            ObjectMetadata objectMetadata = getObjectMetadata(file);
            s3Client.putObject(bucketName, filePath, file.getInputStream(), objectMetadata);
            String fileUrl = String.format("https://%s.s3.amazonaws.com/%s", bucketName, filePath);

            FileReference fileReference = new FileReference(matricula, filePath, fileUrl, descricao);
            documentoRepository.save(fileReference);
            fileUploadResponse = new FileUploadResponse(fileReference);
        } catch (IOException e) {
            log.error("Erro ao acessar o arquivo: ", e);
            throw APIException.build(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao acessar o arquivo");
        } catch (Exception e) {
            log.error("Erro ao fazer upload do arquivo: ", e);
            throw APIException.build(HttpStatus.BAD_REQUEST, "Erro ao fazer upload do arquivo");
        }
        return fileUploadResponse;
    }

    private static ObjectMetadata getObjectMetadata(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        return objectMetadata;
    }

    private static String getFilePath(String numeroMatricula, MultipartFile file) {
        String filePath;
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf('.'))
                : "";
        filePath = String.format("%s/%s-%s%s", numeroMatricula, UUID.randomUUID(), System.currentTimeMillis(), fileExtension);
        return filePath;
    }

    @Override
    public byte[] downloadFile(String objectKey) {
        try {
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, objectKey);
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
        log.info("DocumentoApplicationsService::getAllDocumentsByMatricula");
        Matricula matricula = matriculaRepository.findByNumeroMatricula(numeroMatricula);
        return FileResponse.convert(documentoRepository.findByMatricula(matricula));
    }

    @Override
    @Transactional
    public void deleteFile(String objectKey) {
        s3Client.deleteObject(bucketName, objectKey);
        documentoRepository.deleteByKey(objectKey);
    }

    @Override
    public DocumentoTypeResponse createDocumentoType(DocumentoTypeRequest request) {
        log.info("DocumentoApplicationsService::createDocumentoType");
        DocumentType documentType = documentoRepository.salvar(new DocumentType(request));
        return new DocumentoTypeResponse(documentType);
    }

}


