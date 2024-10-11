package com.rlti.rh.document.domain;

import com.rlti.rh.funcionario.domain.Matricula;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "file_reference")
public class FileReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String key;

    @Column(nullable = false)
    private String url;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "matricula_id", nullable = false)
    private Matricula matricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    public FileReference(Matricula matricula, String key, String fileUrl, String descricao, DocumentType type) {
        this.matricula = matricula;
        this.key = key;
        this.url = fileUrl;
        this.descricao = descricao;
        this.documentType = type;
    }
}


