package com.rlti.rh.funcionario.application.api.response;

import com.rlti.rh.document.domain.FileReference;

import java.util.List;

public record FileReferenceResponse(
        Long documentType,
        String key,
        String descricao,
        String tipoDocumento
) {
    public FileReferenceResponse(FileReference fileReference) {
        this(
                fileReference.getDocumentType().getId(),
                fileReference.getKey(),
                fileReference.getDescricao(),
                fileReference.getDocumentType().getDescricao()
        );
    }

    public static List<FileReferenceResponse> converte(List<FileReference> fileReferences) {
        return fileReferences.stream().map(FileReferenceResponse::new).toList();
    }
}
