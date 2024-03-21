package rlti.com.rh.funcionario.application.api.request;

import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;
import rlti.com.rh.funcionario.domain.enums.EstadoCivil;
import rlti.com.rh.funcionario.domain.enums.GrauDeInstrucao;
import rlti.com.rh.funcionario.domain.enums.Sexo;

import java.time.LocalDate;

@Builder
public record FuncionarioRequest(
        String nomeCompleto,
        @CPF
        String cpf,
        LocalDate dataNascimento,
        GrauDeInstrucao grauDeInstrucao,
        Sexo sexo,
        EstadoCivil estadoCivil
) {
}
