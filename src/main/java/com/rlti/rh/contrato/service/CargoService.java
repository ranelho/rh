package com.rlti.rh.contrato.service;

import com.rlti.rh.contrato.application.request.CargoRequest;
import com.rlti.rh.contrato.application.response.CargoIdResponse;
import com.rlti.rh.contrato.application.response.CargoResponse;

public interface CargoService {
    CargoIdResponse newCargo(CargoRequest request);
    CargoResponse findByNomeCargo(String nomeCargo);
}
