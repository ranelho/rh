package rlti.com.rh.funcionario.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import rlti.com.rh.contrato.application.api.CargoApi;
import rlti.com.rh.contrato.application.api.request.CargoRequest;
import rlti.com.rh.contrato.application.api.response.CargoIdResponse;
import rlti.com.rh.contrato.application.api.response.CargoResponse;
import rlti.com.rh.funcionario.service.CargoService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CargoRestController implements CargoApi {
    private final CargoService cargoService;

    @Override
    public CargoIdResponse novoCargo(CargoRequest request) {
        log.info("CargoRestController.novoCargo");
        return cargoService.novoCargo(request);
    }

    @Override
    public CargoResponse findByNomeCargo(String nomeCargo) {
        log.info("CargoRestController.findByNomeCargo");
        return cargoService.findByNomeCargo(nomeCargo);
    }
}
