package rlti.com.rh.contrato.service;

import rlti.com.rh.contrato.application.request.ContratoDesligamentoRequest;
import rlti.com.rh.contrato.application.request.ContratoRequest;
import rlti.com.rh.contrato.application.response.ContratoIdResponse;

public interface ContratoService {
    ContratoIdResponse newContratoFuncionario(ContratoRequest request);
    void desligamentoFuncionario(String matricula, ContratoDesligamentoRequest desligamentoRequest);
    void renovacaoContrato(String matricula, Integer prazoTotal);
}
