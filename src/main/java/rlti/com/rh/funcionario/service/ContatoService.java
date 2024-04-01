package rlti.com.rh.funcionario.service;

import rlti.com.rh.funcionario.application.api.request.ContatoRequest;

public interface ContatoService {
    void addContatoFuncionario(String cpf, ContatoRequest contatoRequest);
    void updateContato(String cpf, ContatoRequest contatoRequest);
}
