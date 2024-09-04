package com.rlti.rh.contrato.application.request;

import com.rlti.rh.contrato.domain.MotivoDesligamento;

import java.time.LocalDate;

public record ContratoDesligamentoRequest(
        LocalDate dataDesligamento,
        MotivoDesligamento motivoDesligamento,
        String observacao
) {
}
