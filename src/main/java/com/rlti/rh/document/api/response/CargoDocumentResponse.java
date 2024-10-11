package com.rlti.rh.document.api.response;

import com.rlti.rh.document.domain.DocumentCargo;
import com.rlti.rh.document.domain.FileReference;

import java.util.List;
import java.util.Set;

public record CargoDocumentResponse(Long documentType, String descricao, boolean obrigatorio) {

    public CargoDocumentResponse(DocumentCargo documentCargo) {
        this(
                documentCargo.getDocumentType().getId(),
                documentCargo.getDocumentType().getDescricao(),
                documentCargo.isObrigatorio()
        );
    }

    public static List<CargoDocumentResponse> converter(Set<DocumentCargo> documentCargos) {
        return documentCargos.stream().map(CargoDocumentResponse::new).toList();
    }
}
