package com.rlti.rh.document.api;

import com.rlti.rh.document.api.response.FileResponse;
import com.rlti.rh.document.api.response.FileUploadResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Documentos em S3")
@RequestMapping("/s3/document")
public interface DocumentoApi {
    @PostMapping("/upload/{numeroMatricula}")
    @ResponseStatus(code = HttpStatus.CREATED)
    FileUploadResponse uploadFile(@PathVariable String numeroMatricula, @RequestParam("file") MultipartFile file);

    @GetMapping("/download/{filePath}")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<InputStreamResource> downloadFile(String filePath);

    @GetMapping("/allDocuments/{numeroMatricula}")
    @ResponseStatus(code = HttpStatus.OK)
    List<FileResponse> getAllDocumentsByMatricula(@PathVariable String numeroMatricula);
}
