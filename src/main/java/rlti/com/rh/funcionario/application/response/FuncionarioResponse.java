package rlti.com.rh.funcionario.application.response;

import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.utils.Utils;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioResponse(
      Long idFuncionario,
      String nomeCompleto,
      String cpf,
      LocalDate dataNascimento,
      int idade,
      List<MatriculaResponse> matriculas
) {
    public FuncionarioResponse(Funcionario funcionario) {
        this(
                funcionario.getIdFuncionario(),
                funcionario.getNomeCompleto(),
                funcionario.getCpf(),
                funcionario.getDataNascimento(),
                Utils.calcularIdade(funcionario.getDataNascimento()),
                MatriculaResponse.converte(funcionario.getMatriculas())
        );
    }
    public static List<FuncionarioResponse> converte(List<Funcionario> funcionarios) {
        return funcionarios
                .stream()
                .map(FuncionarioResponse::new)
                .toList();
    }
}
