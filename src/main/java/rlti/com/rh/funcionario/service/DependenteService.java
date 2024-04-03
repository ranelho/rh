package rlti.com.rh.funcionario.service;

import rlti.com.rh.funcionario.application.request.DependenteRequest;

public interface DependenteService {
    void newDependenteFuncionario(String cpf, DependenteRequest request);
    void deleteDependenteFuncionario(String cpfDependente);
}
