package com.rlti.rh.folha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.rh.calculo.process.InssResult;
import com.rlti.rh.calculo.process.IrResult;
import com.rlti.rh.codigos.domain.Codigo;
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

    @OneToOne
    @JoinColumn(name = "codigo_id_codigos")
    private Codigo codigo;
    private BigDecimal valorDesconto;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "folha_mensal_id_folha_mensal")
    private FolhaMensal folhaMensal;

    public Descontos(InssResult inssResult, FolhaMensal folhaMensal, Codigo codigo) {
        this.codigo = codigo;
        this.valorDesconto = inssResult.getInssCalculado();
        this.folhaMensal = folhaMensal;
    }

    public Descontos(IrResult irrf, FolhaMensal folhaMensal,  Codigo codigo) {
        this.codigo = codigo;
        this.valorDesconto = irrf.getIrrfCalculado();
        this.folhaMensal = folhaMensal;
    }
}
