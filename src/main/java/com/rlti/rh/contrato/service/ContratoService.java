package com.rlti.rh.contrato.service;

import com.rlti.rh.contrato.application.api.ContratoResponse;
import com.rlti.rh.contrato.application.request.ContratoDesligamentoRequest;
import com.rlti.rh.contrato.application.request.ContratoRequest;
import com.rlti.rh.contrato.application.response.ContratosAtivosResponse;

import java.util.List;

public interface ContratoService {
    ContratoResponse newContratoFuncionario(ContratoRequest request);
    void desligamentoFuncionario(String matricula, ContratoDesligamentoRequest desligamentoRequest);
    void renovacaoContrato(String matricula, Integer prazoTotal);
    com.rlti.rh.contrato.application.api.ContratoResponse findContratoByMatricula(String matricula);
    void addValeTransporte(String matricula, Integer quantidade, Long idAuxilioTransporte);
    List<ContratosAtivosResponse> findContratosAtivos();
}
