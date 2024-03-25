package rlti.com.rh.funcionario.service;

import rlti.com.rh.contrato.application.api.request.CargoRequest;
import rlti.com.rh.contrato.application.api.response.CargoIdResponse;
import rlti.com.rh.contrato.application.api.response.CargoResponse;

public interface CargoService {
    CargoIdResponse novoCargo(CargoRequest request);
    CargoResponse findByNomeCargo(String nomeCargo);
}
