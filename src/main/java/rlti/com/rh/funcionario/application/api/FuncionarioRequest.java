package rlti.com.rh.funcionario.application.api;

import rlti.com.rh.funcionario.domain.Cargo;
import rlti.com.rh.funcionario.domain.EstadoCivil;
import rlti.com.rh.funcionario.domain.GrauMinimo;
import rlti.com.rh.funcionario.domain.Sexo;

import java.time.LocalDate;

public record FuncionarioRequest(
        String nomeCompleto,
        String cpf,
        LocalDate dataNascimento,
        GrauMinimo grau,
        Cargo cargo,
        Sexo sexo,
        EstadoCivil estadoCivil
) {
}
