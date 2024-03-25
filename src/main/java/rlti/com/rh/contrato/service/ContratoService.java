package rlti.com.rh.contrato.service;

import rlti.com.rh.contrato.application.api.response.ContratoIdResponse;
import rlti.com.rh.contrato.application.api.ContratoRequest;

public interface ContratoService {
    ContratoIdResponse novoContrato(ContratoRequest request);
}
