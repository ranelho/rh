package com.rlti.rh.contrato.service;

import com.rlti.rh.contrato.application.api.ContratoResponse;
import com.rlti.rh.contrato.application.request.ContratoDesligamentoRequest;
import com.rlti.rh.contrato.application.request.ContratoRequest;
import com.rlti.rh.contrato.application.response.ContratoIdResponse;

public interface ContratoService {
    ContratoIdResponse newContratoFuncionario(ContratoRequest request);
    void desligamentoFuncionario(String matricula, ContratoDesligamentoRequest desligamentoRequest);
    void renovacaoContrato(String matricula, Integer prazoTotal);
    ContratoResponse findContratoByMatricula(String matricula);
}
