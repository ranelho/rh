package com.rlti.rh.contrato.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.contrato.application.request.CargoRequest;
import com.rlti.rh.contrato.application.response.CargoIdResponse;
import com.rlti.rh.contrato.application.response.CargoResponse;
import com.rlti.rh.contrato.service.CargoService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CargoRestController implements CargoApi {
    private final CargoService cargoService;

    @Override
    public CargoIdResponse newCargo(CargoRequest request) {
        log.info("CargoRestController.novoCargo");
        return cargoService.newCargo(request);
    }

    @Override
    public CargoResponse findByNomeCargo(String nomeCargo) {
        log.info("CargoRestController.findByNomeCargo");
        return cargoService.findByNomeCargo(nomeCargo);
    }
}
