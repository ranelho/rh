package rlti.com.rh.funcionario.application.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;
import rlti.com.rh.funcionario.domain.enums.EstadoCivil;
import rlti.com.rh.funcionario.domain.enums.GrauDeInstrucao;
import rlti.com.rh.funcionario.domain.enums.Sexo;

import java.time.LocalDate;

@Builder
public record FuncionarioRequest(
        @NotNull
        String nomeCompleto,
        @NotNull
        @CPF(message = "cpf inválido")
        String cpf,
        @NotNull
        LocalDate dataNascimento,
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
