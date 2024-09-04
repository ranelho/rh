package com.rlti.rh.contrato.service;

import com.rlti.rh.contrato.application.request.AuxilioTransporteRequest;
import com.rlti.rh.contrato.application.response.AuxilioTransporteResponse;

import java.util.List;

public interface AuxilioTransporteService {
    void newAuxilioTransporte(AuxilioTransporteRequest request);
    List<AuxilioTransporteResponse> findAllAuxilioTransportes();

    void updateAuxilioTransporte(Long id, AuxilioTransporteRequest request);
}
