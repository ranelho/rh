package com.rlti.rh.document.service;

import com.rlti.rh.document.api.response.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentoService {
    FileUploadResponse uploadFile(String numeroMatricula, MultipartFile file);
}
