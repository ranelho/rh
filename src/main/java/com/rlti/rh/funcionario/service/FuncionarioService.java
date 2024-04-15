package com.rlti.rh.funcionario.service;

import com.rlti.rh.funcionario.application.request.ContaPagamentoRequest;
import com.rlti.rh.funcionario.application.request.FormacaoRequest;
import com.rlti.rh.funcionario.application.request.FuncionarioRequest;
import com.rlti.rh.funcionario.application.request.FuncionarioUpdateRequest;
import com.rlti.rh.funcionario.application.response.FuncionarioComFormacaoResponse;
import com.rlti.rh.funcionario.application.response.FuncionarioIdResponse;
import com.rlti.rh.funcionario.application.response.FuncionarioResponse;
import com.rlti.rh.funcionario.domain.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FuncionarioService {
    FuncionarioIdResponse newFuncionario(FuncionarioRequest request);
    FuncionarioResponse findFuncionarioById(Long idFuncionario);
    Page<FuncionarioResponse> findAllFuncionariosByNome(String nome, Pageable pageable);
    void updateFuncionario(String cpf, FuncionarioUpdateRequest request);
    FuncionarioResponse findFuncionarioByMatricula(String matricula);
    FuncionarioResponse findFuncionarioByCpf(String cpf);
    void verificaAniversarioBath();
    Page<Funcionario> findAllFuncionarios(Pageable pageable);
    void newContaPagamento(String cpf, ContaPagamentoRequest contaPagamentoRequest);
    void updateContaPagamento(String cpf, ContaPagamentoRequest contaPagamentoRequest);
    void addFormacao(String cpf, FormacaoRequest formacaoRequest);
    List<FuncionarioComFormacaoResponse> findAllFuncionariosComFormacao();
}
