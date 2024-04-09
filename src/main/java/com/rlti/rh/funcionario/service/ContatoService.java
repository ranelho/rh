package com.rlti.rh.funcionario.service;

import com.rlti.rh.funcionario.application.request.ContatoRequest;

public interface ContatoService {
    void addContatoFuncionario(String cpf, ContatoRequest contatoRequest);
    void updateContato(String cpf, ContatoRequest contatoRequest);
}
