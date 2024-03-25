package rlti.com.rh.contrato.service;

import rlti.com.rh.contrato.application.api.request.SetorRequest;
import rlti.com.rh.contrato.application.api.response.SetorIdReponse;

public interface SetorService {
    SetorIdReponse criaSetor(SetorRequest request);
}
