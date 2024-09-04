package com.rlti.rh.funcionario.application.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;
import com.rlti.rh.funcionario.domain.enums.EstadoCivil;
import com.rlti.rh.funcionario.domain.enums.GrauDeInstrucao;
import com.rlti.rh.funcionario.domain.enums.Sexo;

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
        @NotNull
        GrauDeInstrucao grauDeInstrucao,
        @NotNull
        Sexo sexo,
        @NotNull
        EstadoCivil estadoCivil,
        String nomePai,
        String nomeMae,
        ContatoRequest contatoRequest,
        @Email
        String emailCorporativo
) {
}
