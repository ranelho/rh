package rlti.com.rh.calculo.service;

import rlti.com.rh.calculo.request.SimulacaoInssRequest;
import rlti.com.rh.calculo.response.SimulacaoInssResponse;

public interface CalculoService {
    SimulacaoInssResponse simularCalculoInss(SimulacaoInssRequest request);
}
