package com.rlti.rh.funcionario.domain;

import com.rlti.rh.funcionario.application.request.ContaPagamentoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.rlti.rh.funcionario.domain.enums.TipoConta;

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

    private String banco;
    private String agencia;
    private String numeroConta;
    @Enumerated(EnumType.STRING)    private TipoConta tipoConta;

    public ContaPagamento(ContaPagamentoRequest contaPagamentoRequest) {
        this.banco = contaPagamentoRequest.banco();
        this.agencia = contaPagamentoRequest.agencia();
        this.numeroConta = contaPagamentoRequest.numeroConta();
        this.tipoConta = contaPagamentoRequest.tipoConta();
    }

    public void update(ContaPagamentoRequest contaPagamentoRequest) {
        this.banco = contaPagamentoRequest.banco();
        this.agencia = contaPagamentoRequest.agencia();
        this.numeroConta = contaPagamentoRequest.numeroConta();
        this.tipoConta = contaPagamentoRequest.tipoConta();
    }
}