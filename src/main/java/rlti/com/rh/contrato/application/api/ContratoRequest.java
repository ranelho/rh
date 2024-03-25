package rlti.com.rh.contrato.application.api;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContratoRequest(
        String matricula,
        Long idSetor,
        Long idCargo,
        LocalDate dataAdmissao,
        LocalDateTime dataAssinaturaContrato,
        String observacao
) {
}
