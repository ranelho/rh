package com.rlti.rh.funcionario.application.request;

import com.rlti.rh.funcionario.domain.enums.TipoConta;

public record ContaPagamentoRequest(
        String banco,
        String agencia,
        String numeroConta,
        TipoConta tipoConta
) {
}
