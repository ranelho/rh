package rlti.com.rh.funcionario.repository;

import rlti.com.rh.funcionario.domain.Contato;
import rlti.com.rh.funcionario.domain.Funcionario;

public interface ContatoRepository {
    Contato saveContato(Contato contato);
}
