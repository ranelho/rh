package rlti.com.rh.funcionario.repository;

import rlti.com.rh.funcionario.domain.Matricula;

public interface MatriculaRepository {
    Matricula findByNumeroMatricula(String matricula);

    Matricula novaMatricula(Matricula matricula);
}
