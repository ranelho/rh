package com.rlti.rh.calculo.service;

public interface CalculoService {
    boolean efetuarCalculo(String mesCompetencia);
    void atualizarStatus(String mesCompetencia, String numeroMatricula, boolean status);
    void deleteFolha(String mesCompetencia, String numeroMatricula);
}
