package com.rlti.rh.funcionario.application.response;

import com.rlti.rh.funcionario.domain.Funcionario;
import com.rlti.rh.utils.Utils;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioComFormacaoResponse(
        Long idFuncionario,
        String nomeCompleto,
        String cpf,
        LocalDate dataNascimento,
        int idade,
        List<FormacaoResponse> formacoes
) {
    public FuncionarioComFormacaoResponse(Funcionario funcionario) {
        this(
                funcionario.getIdFuncionario(),
                funcionario.getNomeCompleto(),
                funcionario.getCpf(),
                funcionario.getDataNascimento(),
                Utils.calcularIdade(funcionario.getDataNascimento()),
                FormacaoResponse.converte(funcionario.getFormacao())
        );
    }
    public static List<FuncionarioComFormacaoResponse> converte(List<Funcionario> funcionarios) {
        return funcionarios
                .stream()
                .map(FuncionarioComFormacaoResponse::new)
                .toList();
    }
    public static Page<FuncionarioComFormacaoResponse> convertePageable(Page<Funcionario> clientes) {
        return clientes.map(FuncionarioComFormacaoResponse::new);
    }
}
