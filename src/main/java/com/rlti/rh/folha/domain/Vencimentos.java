package com.rlti.rh.folha.domain;

import com.rlti.rh.codigos.domain.Codigo;
import com.rlti.rh.folha.application.api.VencimentosRequest;
import com.rlti.rh.horas.domain.Horas;
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

    @OneToOne
    @JoinColumn(name = "codigo_id_codigos")
    private Codigo codigo;
    private BigDecimal valorVencimento;
    private Boolean dedutivel;

    @ManyToOne
    @JoinColumn(name = "folha_mensal_id_folha_mensal")
    private FolhaMensal  folhaMensal;

    @ManyToOne
    @JoinColumn(name = "horas_id_horas_trabalhadas")
    private Horas horas;

    public Vencimentos(VencimentosRequest vencimentosRequest, Codigo codigo) {
        this.codigo = codigo;
        this.valorVencimento = vencimentosRequest.getValorVencimento();
        this.dedutivel = vencimentosRequest.getDedutivel();
    }

}
