package com.rlti.rh.document.domain;

import com.rlti.rh.contrato.domain.Cargo;
import com.rlti.rh.document.api.request.CagoDocumentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.function.LongFunction;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class DocumentCargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    private boolean obrigatorio;

    public DocumentCargo(Cargo cargo, DocumentType documentType, boolean obrigatorio) {
        this.cargo = cargo;
        this.documentType = documentType;
        this.obrigatorio = obrigatorio;
    }

    public static List<DocumentCargo> fromRequestList(Cargo cargo,
                                                      List<CagoDocumentRequest> cargoDocumentRequestList,
                                                      LongFunction<DocumentType> findDocumentTypeById) {
        return cargoDocumentRequestList.stream()
                .map(request -> new DocumentCargo(
                        cargo,
                        findDocumentTypeById.apply(request.idDocument()),
                        request.obrigatorio()
                ))
                .toList();
    }
}
