package rlti.com.rh.contrato.service;

import rlti.com.rh.contrato.application.request.CargoRequest;
import rlti.com.rh.contrato.application.response.CargoIdResponse;
import rlti.com.rh.contrato.application.response.CargoResponse;

public interface CargoService {
    CargoIdResponse newCargo(CargoRequest request);
    CargoResponse findByNomeCargo(String nomeCargo);
}
