package com.rlti.rh.codigos.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CODIGO")
public class Codigo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="codigo_seq_generator", sequenceName = "codigo_sequence", allocationSize=1)
    @Column(name = "id_codigos", nullable = false)
    private Long idCodigos;

    @Column(unique = true)
    private String cod;
    @Column(unique = true)
    private String descricao;

}
