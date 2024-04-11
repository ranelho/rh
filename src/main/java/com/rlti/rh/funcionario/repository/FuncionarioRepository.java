package com.rlti.rh.funcionario.repository;

import com.rlti.rh.funcionario.domain.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FuncionarioRepository {
    Funcionario saveFuncionario(Funcionario funcionario);
    Funcionario findFuncionarioById(Long idFuncionario);
    Page<Funcionario> findAllFuncionariosByNome(String nome, Pageable pageable);
    Funcionario findFuncionarioByMatricula(String matricula);
    Funcionario findFuncionarioByCpf(String cpf);
    Page<Funcionario> findAllFuncionarios(Pageable pageable);
    List<Funcionario> findAllByAniversario(int mesAtual, int diaAtual);
}
