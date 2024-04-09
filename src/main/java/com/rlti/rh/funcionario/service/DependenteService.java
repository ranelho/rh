package com.rlti.rh.funcionario.service;

import com.rlti.rh.funcionario.application.request.DependenteRequest;

public interface DependenteService {
    void newDependenteFuncionario(String cpf, DependenteRequest request);
    void deleteDependenteFuncionario(String cpfDependente);
}
