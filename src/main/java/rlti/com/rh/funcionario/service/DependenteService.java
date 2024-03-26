package rlti.com.rh.funcionario.service;

import rlti.com.rh.funcionario.application.api.request.DependenteRequest;

public interface DependenteService {
    void novoDependente(String cpf, DependenteRequest request);
    void removerDependente(String cpfDependente);
}
