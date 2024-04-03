package rlti.com.rh.funcionario.repository;

import rlti.com.rh.funcionario.domain.Dependente;
import rlti.com.rh.funcionario.domain.Funcionario;

public interface DependenteRepository {
    int countDependenteFuncionario(Funcionario funcionario);
    Dependente saveDependente(Dependente dependente);
    Dependente findByCpf(String cpfDependente);
    void deleteDependenteFuncionario(Dependente dependente);
}
