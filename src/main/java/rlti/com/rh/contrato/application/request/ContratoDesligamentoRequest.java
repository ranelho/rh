package rlti.com.rh.contrato.application.request;

import rlti.com.rh.contrato.domain.MotivoDesligamento;

import java.time.LocalDate;

public record ContratoDesligamentoRequest(
        LocalDate dataDesligamento,
        MotivoDesligamento motivoDesligamento,
        String observacao
) {
}
