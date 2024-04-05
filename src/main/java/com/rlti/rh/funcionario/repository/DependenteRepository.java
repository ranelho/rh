package com.rlti.rh.funcionario.repository;

import com.rlti.rh.funcionario.domain.Dependente;
import com.rlti.rh.funcionario.domain.Funcionario;

public interface DependenteRepository {
    int countDependenteFuncionario(Funcionario funcionario);
    Dependente saveDependente(Dependente dependente);
    Dependente findByCpf(String cpfDependente);
    void deleteDependenteFuncionario(Dependente dependente);
}
