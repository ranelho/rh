package com.rlti.rh.contrato.service;

import com.rlti.rh.contrato.application.request.AuxilioTransporteRequest;
import com.rlti.rh.contrato.domain.AuxilioTransporte;
import com.rlti.rh.contrato.repository.AuxilioTransporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuxilioTransporteApplicationService implements AuxilioTransporteService {
    private final AuxilioTransporteRepository repository;

    @Override
    public void newAuxilioTransporte(AuxilioTransporteRequest request) {
        repository.saveAuxilioTransporte(new AuxilioTransporte(request));
    }
}
