package com.rlti.rh.contrato.service;

import com.rlti.rh.contrato.application.request.AuxilioTransporteRequest;
import com.rlti.rh.contrato.application.response.AuxilioTransporteResponse;
import com.rlti.rh.contrato.domain.AuxilioTransporte;
import com.rlti.rh.contrato.repository.AuxilioTransporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuxilioTransporteApplicationService implements AuxilioTransporteService {
    private final AuxilioTransporteRepository repository;

    @Override
    public void newAuxilioTransporte(AuxilioTransporteRequest request) {
        repository.saveAuxilioTransporte(new AuxilioTransporte(request));
    }

    @Override
    public List<AuxilioTransporteResponse> findAllAuxilioTransportes() {
        return repository.findAllAuxilioTransportes().stream().map(AuxilioTransporteResponse::new).toList();
    }

    @Override
    public void updateAuxilioTransporte(Long id, AuxilioTransporteRequest request) {
        AuxilioTransporte auxilioTransporte = repository.findById(id);
        auxilioTransporte.update(request);
        repository.saveAuxilioTransporte(auxilioTransporte);
    }
}
