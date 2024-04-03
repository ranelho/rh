package rlti.com.rh.contrato.service;

import rlti.com.rh.contrato.application.request.SetorRequest;
import rlti.com.rh.contrato.application.response.SetorIdReponse;

public interface SetorService {
    SetorIdReponse newSetor(SetorRequest request);
}
