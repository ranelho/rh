package com.rlti.rh.funcionario.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import com.rlti.rh.funcionario.domain.enums.EstadoCivil;
import com.rlti.rh.funcionario.domain.enums.GrauDeInstrucao;
import com.rlti.rh.funcionario.domain.enums.Sexo;

import java.time.LocalDate;

@Builder
public record FuncionarioUpdateRequest(
        @NotNull
        String nomeCompleto,
        @NotNull
        String rg,
        LocalDate dataEmissaoRg,
        String ctps,
        String pis,
        GrauDeInstrucao grauDeInstrucao,
        @NotNull
        Sexo sexo,
        @NotNull
        EstadoCivil estadoCivil
) {
}
