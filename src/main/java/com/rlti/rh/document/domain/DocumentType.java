package com.rlti.rh.document.domain;

import com.rlti.rh.contrato.domain.Cargo;
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
    private String name;

    private Boolean obrigatorio;

    @OneToMany(mappedBy = "documentType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FileReference> fileReferences;

    public DocumentType(DocumentoTypeRequest request) {
        this.name = request.name().toUpperCase();
        this.obrigatorio = request.obrigatorio();
    }
}
