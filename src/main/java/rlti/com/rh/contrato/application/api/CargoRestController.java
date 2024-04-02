package rlti.com.rh.contrato.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import rlti.com.rh.contrato.application.request.CargoRequest;
import rlti.com.rh.contrato.application.response.CargoIdResponse;
import rlti.com.rh.contrato.application.response.CargoResponse;
import rlti.com.rh.contrato.service.CargoService;

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
