package com.rlti.rh.document.api;

import com.rlti.rh.document.api.response.FileResponse;
import com.rlti.rh.document.api.response.FileUploadResponse;
import com.rlti.rh.document.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DocumentoRestController implements DocumentoApi {

    private final DocumentoService documentoService;

    @Override
    public FileUploadResponse uploadFile(String numeroMatricula, String descricao, MultipartFile file) {
        return documentoService.uploadFile(numeroMatricula,descricao, file);
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadFile(String filePath) {
        byte[] fileData = documentoService.downloadFile(filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

        ByteArrayInputStream bis = new ByteArrayInputStream(fileData);
        return new ResponseEntity<>(new InputStreamResource(bis), headers, HttpStatus.OK);
    }

    @Override
    public List<FileResponse> getAllDocumentsByMatricula(String numeroMatricula) {
        return documentoService.getAllDocumentsByMatricula(numeroMatricula);
    }

    @Override
    public void deleteFile(String filePath) {
        documentoService.deleteFile(filePath);
    }

}
