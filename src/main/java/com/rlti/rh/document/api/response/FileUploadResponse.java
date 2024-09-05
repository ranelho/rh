package com.rlti.rh.document.api.response;

import com.rlti.rh.document.domain.FileReference;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileUploadResponse {
    private String filePath;
    private String fileUrl;
    private String descricao;
    private LocalDateTime dateTime;

    public FileUploadResponse(FileReference fileReference) {
        filePath = fileReference.getKey();
        fileUrl =  fileReference.getUrl();
        descricao = fileReference.getDescricao();
        dateTime = LocalDateTime.now();
    }
}