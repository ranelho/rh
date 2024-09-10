package com.rlti.rh.document.api.response;

import com.rlti.rh.document.domain.DocumentType;

public record DocumentoTypeResponse(Long id, String nome, Boolean obrigatorio) {
    public DocumentoTypeResponse(DocumentType documentType) {
        this(documentType.getId(), documentType.getName(), documentType.getObrigatorio());
    }
}
