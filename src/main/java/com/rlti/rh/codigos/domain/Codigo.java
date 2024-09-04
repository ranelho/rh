package com.rlti.rh.codigos.domain;

import com.rlti.rh.codigos.application.api.CodigoRequest;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigo_seq_generator")
    @SequenceGenerator(name="codigo_seq_generator", sequenceName = "codigo_sequence", allocationSize=1)
    @Column(name = "id_codigo", nullable = false)
    private Long idCodigo;

    @Column(unique = true)
    private String cod;
    private String descricao;

    public Codigo(CodigoRequest codigoRequest) {
        this.cod = codigoRequest.cod().toUpperCase();
        this.descricao = codigoRequest.descricao().toUpperCase();
    }
}
