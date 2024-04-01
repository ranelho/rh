package rlti.com.rh.contrato.service;

import rlti.com.rh.contrato.application.api.response.ContratoIdResponse;
import rlti.com.rh.contrato.application.api.ContratoRequest;
import rlti.com.rh.funcionario.application.api.request.ContatoRequest;

public interface ContratoService {
    ContratoIdResponse novoContrato(ContratoRequest request);
}
