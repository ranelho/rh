package rlti.com.rh.calculo.service;

import rlti.com.rh.calculo.application.api.request.SimulacaoInssRequest;
import rlti.com.rh.calculo.application.api.response.SimulacaoInssResponse;

public interface CalculoService {
    SimulacaoInssResponse simularCalculoInss(SimulacaoInssRequest request);
}
