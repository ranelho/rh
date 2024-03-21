package rlti.com.rh.funcionario.repository;

import rlti.com.rh.funcionario.domain.Funcionario;

import java.util.List;

public interface FuncionarioRepository {
    Funcionario save(Funcionario funcionario);
    Funcionario findFuncionarioById(Long idFuncionario);
    List<Funcionario> findFuncionariosByNome(String nome);
}
