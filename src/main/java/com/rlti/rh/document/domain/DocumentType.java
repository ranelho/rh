package com.rlti.rh.document.domain;

import com.rlti.rh.document.api.request.DocumentoTypeRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "document_type")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String descricao;

    @OneToMany(mappedBy = "documentType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CargoDocument> cargoDocuments;

    public DocumentType(DocumentoTypeRequest request) {
        this.descricao = request.descricao().toUpperCase();
    }
}
