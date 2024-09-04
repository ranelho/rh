package com.rlti.rh.funcionario.repository;

import com.rlti.rh.funcionario.domain.Matricula;

public interface MatriculaRepository {
    Matricula findByNumeroMatricula(String matricula);
    Matricula novaMatricula(Matricula matricula);
    int lastMatricula();
}
