package rlti.com.rh.funcionario.repository;

import rlti.com.rh.funcionario.domain.Funcionario;

import java.util.List;

public interface FuncionarioRepository {
    Funcionario salvaFuncionario(Funcionario funcionario);
    Funcionario findFuncionarioById(Long idFuncionario);
    List<Funcionario> findFuncionariosByNome(String nome);
    Funcionario findFuncionarioByMatricula(String matricula);
    Funcionario findByCpf(String cpf);
}
