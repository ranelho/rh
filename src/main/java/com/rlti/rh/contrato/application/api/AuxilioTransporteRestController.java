package com.rlti.rh.contrato.application.api;

import com.rlti.rh.contrato.application.request.AuxilioTransporteRequest;
import com.rlti.rh.contrato.application.response.AuxilioTransporteResponse;
import com.rlti.rh.contrato.service.AuxilioTransporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuxilioTransporteRestController implements AuxilioTransporteApi {
    private final AuxilioTransporteService auxilioTransporteService;

    @Override
    public void newAuxilioTransporte(AuxilioTransporteRequest request) {
        auxilioTransporteService.newAuxilioTransporte(request);
    }

    @Override
    public List<AuxilioTransporteResponse> findAllAuxilioTransportes() {
        return auxilioTransporteService.findAllAuxilioTransportes();
    }

    @Override
    public void updateAuxilioTransporte(Long id, AuxilioTransporteRequest request) {
        auxilioTransporteService.updateAuxilioTransporte(id, request);
    }
}
