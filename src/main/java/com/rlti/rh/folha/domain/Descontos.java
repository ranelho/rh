package com.rlti.rh.folha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.rh.calculo.InssResult;
import com.rlti.rh.calculo.IrResult;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "DESCONTOS")
public class Descontos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "descontos_seq_generator", sequenceName = "descontos_sequence", allocationSize = 1)
    @Column(name = "id_desconto", nullable = false)
    private Long idDesconto;

    private String codigo;
    private String descricao;
    private BigDecimal valorDesconto;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "folha_mensal_id_folha_mensal")
    private FolhaMensal folhaMensal;

    public Descontos(InssResult inssResult, FolhaMensal folhaMensal) {
        this.codigo = inssResult.getCodigo();
        this.descricao = inssResult.getDescricao();
        this.valorDesconto = inssResult.getInssCalculado();
        this.folhaMensal = folhaMensal;
    }

    public Descontos(IrResult irrf, FolhaMensal folhaMensal) {
        this.codigo = irrf.getCodigo();
        this.descricao = irrf.getDescricao();
        this.valorDesconto = irrf.getIrrfCalculado();
        this.folhaMensal = folhaMensal;
    }
}
