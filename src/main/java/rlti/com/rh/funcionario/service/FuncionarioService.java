package rlti.com.rh.funcionario.service;

import rlti.com.rh.funcionario.application.request.FuncionarioRequest;
import rlti.com.rh.funcionario.application.request.FuncionarioUpdateRequest;
import rlti.com.rh.funcionario.application.response.FuncionarioIdResponse;
import rlti.com.rh.funcionario.application.response.FuncionarioResponse;
import rlti.com.rh.funcionario.domain.Funcionario;

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
}
