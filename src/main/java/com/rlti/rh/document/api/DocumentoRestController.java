package com.rlti.rh.document.api;

import com.rlti.rh.document.api.response.FileUploadResponse;
import com.rlti.rh.document.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class DocumentoRestController implements DocumentoApi {

    private final DocumentoService documentoService;

    @Override
    public ResponseEntity<FileUploadResponse> uploadFile(String numeroMatricula, MultipartFile file) {
        return new ResponseEntity<>(documentoService.uploadFile(numeroMatricula, file), HttpStatus.OK);
    }
}
