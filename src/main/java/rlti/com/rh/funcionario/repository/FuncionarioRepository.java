package rlti.com.rh.funcionario.repository;

import rlti.com.rh.funcionario.domain.Funcionario;

import java.util.List;

public interface FuncionarioRepository {
    Funcionario saveFuncionario(Funcionario funcionario);
    Funcionario findFuncionarioById(Long idFuncionario);
    List<Funcionario> findAllFuncionariosByNome(String nome);
    Funcionario findFuncionarioByMatricula(String matricula);
    Funcionario findFuncionarioByCpf(String cpf);
}
