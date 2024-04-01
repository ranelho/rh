package rlti.com.rh.funcionario.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rlti.com.rh.funcionario.domain.enums.TipoConta;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CONTA_PAGAMENTO")
public class ContaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="conta_pagamento_seq_generator", sequenceName = "conta_pagamento_sequence", allocationSize=1)
    @Column(name = "id_conta_pagamento", nullable = false)
    private Long idContaPagamento;

    private String agencia;
    private String numeroConta;
    @Enumerated(EnumType.STRING)    private TipoConta tipoConta;

}