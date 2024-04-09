package com.rlti.rh.funcionario.repository;

import com.rlti.rh.funcionario.domain.Funcionario;

import java.util.List;

public interface FuncionarioRepository {
    Funcionario saveFuncionario(Funcionario funcionario);
    Funcionario findFuncionarioById(Long idFuncionario);
    List<Funcionario> findAllFuncionariosByNome(String nome);
    Funcionario findFuncionarioByMatricula(String matricula);
    Funcionario findFuncionarioByCpf(String cpf);
    List<Funcionario> findAllFuncionarios();
    List<Funcionario> findAllByAniversario(int mesAtual, int diaAtual);
}
