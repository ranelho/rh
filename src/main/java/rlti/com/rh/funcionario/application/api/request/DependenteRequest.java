package rlti.com.rh.funcionario.application.api.request;

import org.hibernate.validator.constraints.br.CPF;
import rlti.com.rh.funcionario.domain.enums.GrauParentesco;

import java.time.LocalDate;

public record DependenteRequest(
        String nomeCompleto,
        @CPF
        String cpf,
        LocalDate dataNascimento,
        GrauParentesco grauParentesco
) {
}
