package com.rlti.rh.calculo.service;

import com.rlti.rh.calculo.application.api.request.SimulacaoInssRequest;
import com.rlti.rh.calculo.application.api.response.SimulacaoResponse;

public interface CalculoService {
    SimulacaoResponse simularCalculoInss(SimulacaoInssRequest request);
}
