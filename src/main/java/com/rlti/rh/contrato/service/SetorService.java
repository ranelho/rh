package com.rlti.rh.contrato.service;

import com.rlti.rh.contrato.application.request.SetorRequest;
import com.rlti.rh.contrato.application.response.SetorIdReponse;

public interface SetorService {
    SetorIdReponse newSetor(SetorRequest request);
}
