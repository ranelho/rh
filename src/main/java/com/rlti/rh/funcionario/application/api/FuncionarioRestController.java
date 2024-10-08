package com.rlti.rh.funcionario.application.api;

import com.rlti.rh.funcionario.application.request.ContaPagamentoRequest;
import com.rlti.rh.funcionario.application.request.FormacaoRequest;
import com.rlti.rh.funcionario.application.request.FuncionarioRequest;
import com.rlti.rh.funcionario.application.request.FuncionarioUpdateRequest;
import com.rlti.rh.funcionario.application.response.FuncionarioComFormacaoResponse;
import com.rlti.rh.funcionario.application.response.FuncionarioIdResponse;
import com.rlti.rh.funcionario.application.response.FuncionarioResponse;
import com.rlti.rh.funcionario.domain.Funcionario;
import com.rlti.rh.funcionario.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FuncionarioRestController implements FuncionarioApi {

    private final FuncionarioService funcionarioService;

    @Override
    public FuncionarioIdResponse newFuncionario(FuncionarioRequest request) {
        return funcionarioService.newFuncionario(request);
    }

    @Override
    public Page<FuncionarioResponse> findAllFuncionariosByNome(String nome, Pageable pageable) {
        return funcionarioService.findAllFuncionariosByNome(nome, pageable);
    }

    @Override
    public Page<FuncionarioResponse> findAllFuncionarios(Pageable pageable) {
        Page<Funcionario> funcionarios = funcionarioService.findAllFuncionarios(pageable);
        return FuncionarioResponse.convertePageable(funcionarios);
    }

    @Override
    public void updateFuncionario(String cpf, FuncionarioUpdateRequest request) {
        funcionarioService.updateFuncionario(cpf, request);
    }

    @Override
    public FuncionarioResponse findFuncionarioByMatricula(String matricula) {
        return funcionarioService.findFuncionarioByMatricula(matricula);
    }

    @Override
    public FuncionarioResponse findFuncionarioByCpf(String cpf) {
        return funcionarioService.findFuncionarioByCpf(cpf);
    }

    @Override
    public void newContaPagamento(String cpf, ContaPagamentoRequest contaPagamentoRequest) {
        funcionarioService.newContaPagamento(cpf, contaPagamentoRequest);
    }

    @Override
    public void updateContaPagamento(String cpf, ContaPagamentoRequest contaPagamentoRequest) {
        funcionarioService.updateContaPagamento(cpf, contaPagamentoRequest);
    }

    @Override
    public void addFormacao(String cpf, FormacaoRequest formacaoRequest) {
        funcionarioService.addFormacao(cpf, formacaoRequest);
    }

    @Override
    public List<FuncionarioComFormacaoResponse> findAllFuncionariosComFormacao() {
        return funcionarioService.findAllFuncionariosComFormacao();
    }


}
