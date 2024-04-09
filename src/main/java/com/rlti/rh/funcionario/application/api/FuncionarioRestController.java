package com.rlti.rh.funcionario.application.api;

import com.rlti.rh.funcionario.application.request.ContaPagamentoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.funcionario.application.request.FuncionarioRequest;
import com.rlti.rh.funcionario.application.request.FuncionarioUpdateRequest;
import com.rlti.rh.funcionario.application.response.FuncionarioIdResponse;
import com.rlti.rh.funcionario.application.response.FuncionarioResponse;
import com.rlti.rh.funcionario.service.FuncionarioService;

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
    public FuncionarioResponse findFuncionarioById(Long idFuncionario) {
        return funcionarioService.findFuncionarioById(idFuncionario);
    }

    @Override
    public List<FuncionarioResponse> findAllFuncionariosByNome(String nome) {
        return funcionarioService.findAllFuncionariosByNome(nome);
    }

    @Override
    public void updateFuncionario(Long id, FuncionarioUpdateRequest request) {
        funcionarioService.updateFuncionario(id, request);
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


}
