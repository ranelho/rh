package rlti.com.rh.funcionario.repository;

import rlti.com.rh.funcionario.domain.Funcionario;

import java.util.Optional;

public interface FuncionarioRepository {
    Funcionario save(Funcionario funcionario);
}
