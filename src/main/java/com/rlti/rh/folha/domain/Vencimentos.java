package com.rlti.rh.folha.domain;

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
@Entity(name = "VENCIMENTOS")
public class Vencimentos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="vencimentos_seq_generator", sequenceName = "vencimentos_sequence", allocationSize=1)
    @Column(name = "id_vencimento", nullable = false)
    private Long idVencimento;

    private String codigo;
    private String descricao;
    private BigDecimal valorVencimento;

    @ManyToOne
    @JoinColumn(name = "folha_mensal_id_folha_mensal")
    private FolhaMensal folhaMensal;

    public Vencimentos(BigDecimal salarioFuncionario, FolhaMensal folhaMensal, String codigo, String descricao) {
        this.valorVencimento = salarioFuncionario;
        this.folhaMensal = folhaMensal;
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
