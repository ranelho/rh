package com.rlti.rh.contrato.application.request;

import com.rlti.rh.contrato.domain.Prazo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContratoRequest(
        String matricula,
        Long idSetor,
        Long idCargo,
        LocalDate dataAdmissao,
        LocalDateTime dataAssinaturaContrato,
        String observacao,
        Prazo prazo,
        Integer prazoTotalEmMeses
) {
}
