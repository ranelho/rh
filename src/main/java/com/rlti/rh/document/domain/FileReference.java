package com.rlti.rh.document.domain;

import com.rlti.rh.funcionario.domain.Matricula;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "file_reference")
public class FileReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String key;

    @Column(nullable = false)
    private String url;

    @OneToOne
    @JoinColumn(name = "matricula_id_matricula")
    private Matricula matricula;

    public FileReference(Matricula matricula, String key, String fileUrl) {
        this.matricula = matricula;
        this.key = key;
        this.url = fileUrl;
    }
}


