package com.rlti.rh.funcionario.application.api.response;

import com.rlti.rh.document.api.response.DocumentoTypeResponse;
import com.rlti.rh.document.domain.FileReference;

import java.util.List;

public record FileReferenceResponse(
        String key,
        String descricao,
        DocumentoTypeResponse tipoDocumento
) {
    public FileReferenceResponse(FileReference fileReference) {
        this(
                fileReference.getKey(),
                fileReference.getDescricao(),
                new DocumentoTypeResponse(fileReference.getDocumentType())
        );
    }

    public static List<FileReferenceResponse> converte(List<FileReference> fileReferences) {
        return fileReferences.stream().map(FileReferenceResponse::new).toList();
    }
}
