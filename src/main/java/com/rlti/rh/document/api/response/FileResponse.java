package com.rlti.rh.document.api.response;

import com.rlti.rh.document.domain.FileReference;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FileResponse {
    private String filePath;
    private String fileUrl;

    public FileResponse(FileReference fileReference) {
        this.filePath = fileReference.getKey();
        this.fileUrl = fileReference.getUrl();
    }

    public static List<FileResponse> convert(List<FileReference> fileReferences) {
        return fileReferences.stream().map(FileResponse::new).toList();
    }
}
