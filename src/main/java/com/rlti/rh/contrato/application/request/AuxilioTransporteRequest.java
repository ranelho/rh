package com.rlti.rh.contrato.application.request;

import java.time.LocalDate;

public record AuxilioTransporteRequest(
        Double valorUnitario,
        String descricao,
        LocalDate inicioVigencia,
        LocalDate fimVigencia
) {
}
