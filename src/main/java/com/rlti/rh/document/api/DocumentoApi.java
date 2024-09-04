package com.rlti.rh.document.api;

import com.rlti.rh.document.api.response.FileUploadResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Documentos em S3")
@RequestMapping("/s3/document")
public interface DocumentoApi {

    @PostMapping("/upload/{numeroMatricula}")
    ResponseEntity<FileUploadResponse> uploadFile(@PathVariable String numeroMatricula, @RequestParam("file") MultipartFile file) ;
}
