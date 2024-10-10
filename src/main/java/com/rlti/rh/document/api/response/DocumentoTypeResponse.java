package com.rlti.rh.document.api.response;

import com.rlti.rh.document.domain.DocumentType;
import lombok.Builder;

import java.util.List;

@Builder
public record DocumentoTypeResponse(Long id, String descricao) {
    public DocumentoTypeResponse(DocumentType documentType) {
        this(documentType.getId(), documentType.getDescricao());
    }

    public static List<DocumentoTypeResponse> convert(List<DocumentType> documentType) {
        return documentType.stream().map(DocumentoTypeResponse::new).toList();
    }
}
