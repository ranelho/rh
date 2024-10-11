package com.rlti.rh.funcionario.application.api.response;

import com.rlti.rh.funcionario.domain.Funcionario;
import com.rlti.rh.utils.Utils;
import org.springframework.data.domain.Page;

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
    public static Page<FuncionarioResponse> convertePageable(Page<Funcionario> clientes) {
        return clientes.map(FuncionarioResponse::new);
    }
}
