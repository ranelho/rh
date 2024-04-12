package com.rlti.rh.folha.domain;

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
@Entity(name = "VENCIMENTOS_FOLHA")
public class VencimentosFolha {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="vencimentos_folha_seq_generator", sequenceName = "vencimentos_folha_sequence", allocationSize=1)
    @Column(name = "id_vencimento", nullable = false)
    private Long idVencimentoFolha;

    @OneToOne
    @JoinColumn(name = "codigo_id_codigos")
    private Codigo codigo;
    private BigDecimal valorVencimento;
    private Boolean dedutivel;


    @ManyToOne
    @JoinColumn(name = "folha_mensal_id_folha_mensal")
    private FolhaMensal  folhaMensal;

}
