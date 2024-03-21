package rlti.com.rh.funcionario.service;

import rlti.com.rh.funcionario.application.api.request.FuncionarioRequest;
import rlti.com.rh.funcionario.application.api.response.FuncionarioIdResponse;
import rlti.com.rh.funcionario.application.api.response.FuncionarioResponse;

import java.util.List;

public interface FuncionarioService {
    FuncionarioIdResponse novoFuncionario(FuncionarioRequest request);
    FuncionarioResponse findFuncionarioById(Long idFuncionario);
    List<FuncionarioResponse> findFuncionariosByNome(String nome);
}