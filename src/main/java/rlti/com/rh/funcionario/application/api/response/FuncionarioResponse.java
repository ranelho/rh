package rlti.com.rh.funcionario.application.api.response;

import rlti.com.rh.funcionario.domain.Funcionario;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioResponse(
      Long idFuncionario,
      String nomeCompleto,
      String cpf,
      LocalDate dataNascimento,
      Long matricula
) {
    public FuncionarioResponse(Funcionario funcionario) {
        this(
                funcionario.getIdFuncionario(),
                funcionario.getNomeCompleto(),
                funcionario.getCpf(),
                funcionario.getDataNascimento(),
                funcionario.getMatricula()
        );
    }
    public static List<FuncionarioResponse> converte(List<Funcionario> funcionarios) {
        return funcionarios
                .stream()
                .map(FuncionarioResponse::new)
                .toList();
    }
}
