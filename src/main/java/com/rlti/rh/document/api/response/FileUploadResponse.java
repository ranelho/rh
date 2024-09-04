package com.rlti.rh.document.api.response;

import com.rlti.rh.document.domain.FileReference;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileUploadResponse {
    private String filePath;
    private String fileUrl;
    private LocalDateTime dateTime;

    public FileUploadResponse(FileReference fileReference) {
        filePath = fileReference.getKey();
        fileUrl =  fileReference.getUrl();
        dateTime = LocalDateTime.now();
    }
}