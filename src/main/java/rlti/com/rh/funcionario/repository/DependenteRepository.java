package rlti.com.rh.funcionario.repository;

import rlti.com.rh.funcionario.domain.Dependente;
import rlti.com.rh.funcionario.domain.Funcionario;

public interface DependenteRepository {
    int countDependenteFuncionario(Funcionario funcionario);
    Dependente salvaDependente(Dependente dependente);
    Dependente findByCpf(String cpfDependente);
    void removerDependente(Dependente dependente);
}
