package com.rlti.rh.contrato.application.api;

import com.rlti.rh.contrato.application.request.AuxilioTransporteRequest;
import com.rlti.rh.contrato.service.AuxilioTransporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuxilioTransporteRestController implements AuxilioTransporteApi {
    private final AuxilioTransporteService auxilioTransporteService;

    @Override
    public void newAuxilioTransporte(AuxilioTransporteRequest request) {
        auxilioTransporteService.newAuxilioTransporte(request);
    }
}
