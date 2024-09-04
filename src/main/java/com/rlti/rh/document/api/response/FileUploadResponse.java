package com.rlti.rh.document.api.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileUploadResponse {
    private String filePath;
    private String fileUrl;
    private LocalDateTime dateTime;
}