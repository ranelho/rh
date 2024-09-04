package com.rlti.rh.contrato.service;

import com.rlti.rh.contrato.application.request.SetorRequest;
import com.rlti.rh.contrato.application.response.SetorIdReponse;
import com.rlti.rh.contrato.application.response.SetorResponse;

import java.util.List;

public interface SetorService {
    SetorIdReponse newSetor(SetorRequest request);
    List<SetorResponse> getAllSetores();
}
