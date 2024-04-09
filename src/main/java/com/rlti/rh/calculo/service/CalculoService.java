package com.rlti.rh.calculo.service;

import com.rlti.rh.calculo.application.api.request.SimulacaoInssRequest;
import com.rlti.rh.calculo.application.api.response.SimulacaoResponse;

public interface CalculoService {
    SimulacaoResponse simularCalculoInss(SimulacaoInssRequest request);
    boolean efetuarCalculo(String mesCompetencia);
    void atualizarStatus(String mesCompetencia, String numeroMatricula, boolean status);
}