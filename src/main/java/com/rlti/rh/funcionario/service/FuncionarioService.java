package com.rlti.rh.funcionario.service;

import com.rlti.rh.funcionario.application.request.ContaPagamentoRequest;
import com.rlti.rh.funcionario.application.request.FuncionarioRequest;
import com.rlti.rh.funcionario.application.request.FuncionarioUpdateRequest;
import com.rlti.rh.funcionario.application.response.FuncionarioIdResponse;
import com.rlti.rh.funcionario.application.response.FuncionarioResponse;
import com.rlti.rh.funcionario.domain.Funcionario;

import java.util.List;

public interface FuncionarioService {
    FuncionarioIdResponse newFuncionario(FuncionarioRequest request);
    FuncionarioResponse findFuncionarioById(Long idFuncionario);
    List<FuncionarioResponse> findAllFuncionariosByNome(String nome);
    void updateFuncionario(Long id, FuncionarioUpdateRequest request);
    FuncionarioResponse findFuncionarioByMatricula(String matricula);
    FuncionarioResponse findFuncionarioByCpf(String cpf);
    void verificaAniversarioBath();
    List<Funcionario> findAllFuncionarios();
    void newContaPagamento(String cpf, ContaPagamentoRequest contaPagamentoRequest);
}
