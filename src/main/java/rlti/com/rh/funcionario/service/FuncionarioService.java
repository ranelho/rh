package rlti.com.rh.funcionario.service;

import rlti.com.rh.funcionario.application.api.request.FuncionarioRequest;
import rlti.com.rh.funcionario.application.api.response.FuncionarioIdResponse;

public interface FuncionarioService {
    FuncionarioIdResponse novoFuncionario(FuncionarioRequest request);
}
