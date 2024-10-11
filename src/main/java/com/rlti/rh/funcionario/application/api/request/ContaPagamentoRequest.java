package com.rlti.rh.funcionario.application.api.request;

import com.rlti.rh.funcionario.domain.enums.TipoConta;
import jakarta.validation.constraints.NotNull;

public record ContaPagamentoRequest(
        @NotNull
        String banco,
        @NotNull
        String agencia,
        @NotNull
        String numeroConta,
        @NotNull
        TipoConta tipoConta
) {
}
