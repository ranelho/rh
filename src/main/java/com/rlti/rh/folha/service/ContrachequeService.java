package com.rlti.rh.folha.service;

import com.rlti.rh.folha.application.api.ContrachequeRequest;
import com.rlti.rh.folha.application.api.ContrachequeResponse;

public interface ContrachequeService {
    ContrachequeResponse getContracheque(ContrachequeRequest request);
}
