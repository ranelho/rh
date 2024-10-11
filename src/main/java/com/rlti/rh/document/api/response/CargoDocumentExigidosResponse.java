package com.rlti.rh.document.api.response;

import com.rlti.rh.document.domain.DocumentCargo;
import com.rlti.rh.document.domain.FileReference;

import java.util.List;
import java.util.Set;

public record CargoDocumentExigidosResponse(
        Long documentType,
        String tipoDocumento,
        String descricao,
        String url,
        boolean obrigatorio,
        boolean entregue) {

    public CargoDocumentExigidosResponse(DocumentCargo documentCargo, FileReference fileReference, boolean entregue) {
        this(
                documentCargo.getDocumentType().getId(),
                documentCargo.getDocumentType().getDescricao(),
                fileReference != null ? fileReference.getDescricao() : null,
                fileReference != null ? fileReference.getUrl() : null,
                documentCargo.isObrigatorio(),
                entregue
        );
    }

    public static List<CargoDocumentExigidosResponse> converter(Set<DocumentCargo> documentCargos, List<FileReference> fileReferences) {
        return documentCargos.stream()
                .map(documentCargo -> {
                    FileReference fileReference = fileReferences.stream()
                            .filter(file -> file.getDocumentType().getId().equals(documentCargo.getDocumentType().getId()))
                            .findFirst()
                            .orElse(null);

                    boolean entregue = (fileReference != null);

                    return new CargoDocumentExigidosResponse(documentCargo, fileReference, entregue);
                })
                .toList();
    }
}

