package rlti.com.rh.contrato.service;

import rlti.com.rh.contrato.application.api.ContratoRequest;
import rlti.com.rh.contrato.application.api.response.ContratoIdResponse;

public interface ContratoService {
    ContratoIdResponse novoContrato(ContratoRequest request);
}
