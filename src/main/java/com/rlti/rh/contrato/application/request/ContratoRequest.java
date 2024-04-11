package com.rlti.rh.contrato.application.request;

import com.rlti.rh.contrato.domain.Prazo;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContratoRequest(
        @CPF
        String cpf,
        Long idSetor,
        Long idCargo,
        LocalDate dataAdmissao,
        LocalDateTime dataAssinaturaContrato,
        String observacao,
        Prazo prazo,
        Integer prazoTotalEmMeses,
        Integer nivel
) {
}
