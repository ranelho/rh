package com.rlti.rh.contrato.application.api;

import com.rlti.rh.contrato.domain.Contrato;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContratoResponse(
       Long numeroContrato,
       LocalDate dataAdmissao,
       LocalDate periodoAvaliacao,
       LocalDateTime dataAssinaturaContrato,
       LocalDate dataDesligamento,
       String motivoDesligamento,
       String observacao,
       int prazoTotalEmMeses,
       LocalDate previsaoFimContrato,
       String numeroMatricula
) {
    public ContratoResponse(Contrato contrato) {
        this(
                contrato.getIdContrato(),
                contrato.getDataAdmissao(),
                contrato.getPeriodoAvaliacao(),
                contrato.getDataAssinaturaContrato(),
                contrato.getDataDesligamento(),
                contrato.getMotivoDesligamento() != null ? contrato.getMotivoDesligamento().toString() : null,
                contrato.getObservacao(),
                contrato.getPrazoTotalEmMeses(),
                contrato.getPrevisaoFimContrato(),
                contrato.getMatricula().getNumeroMatricula()
        );
    }
}
