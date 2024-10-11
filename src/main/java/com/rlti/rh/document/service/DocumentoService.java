package com.rlti.rh.document.service;

import com.rlti.rh.document.api.request.DocumentoTypeRequest;
import com.rlti.rh.document.api.response.DocumentoTypeResponse;
import com.rlti.rh.document.api.response.FileResponse;
import com.rlti.rh.document.api.response.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentoService {
    FileUploadResponse uploadFile(String numeroMatricula, Long documentType, String descricao, MultipartFile file);

    byte[] downloadFile(String filePath);

    List<FileResponse> getAllDocumentsByMatricula(String numeroMatricula);

    void deleteFile(String filePath);

    DocumentoTypeResponse createDocumentoType(DocumentoTypeRequest request);
}
