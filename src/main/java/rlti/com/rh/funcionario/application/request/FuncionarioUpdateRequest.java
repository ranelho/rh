package rlti.com.rh.funcionario.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import rlti.com.rh.funcionario.domain.enums.EstadoCivil;
import rlti.com.rh.funcionario.domain.enums.GrauDeInstrucao;
import rlti.com.rh.funcionario.domain.enums.Sexo;

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
