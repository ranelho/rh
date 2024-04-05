package com.rlti.rh.folha.service;

import com.rlti.rh.folha.application.ContrachequeRequest;
import com.rlti.rh.folha.application.ContrachequeResponse;

public interface ContrachequeService {
    ContrachequeResponse getContracheque(ContrachequeRequest request);
}
