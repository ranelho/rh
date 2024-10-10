package com.rlti.rh.contrato.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.rlti.rh.contrato.application.request.CargoRequest;
import com.rlti.rh.contrato.application.response.CargoIdResponse;
import com.rlti.rh.contrato.application.response.CargoResponse;
import com.rlti.rh.contrato.domain.Cargo;
import com.rlti.rh.funcionario.domain.SalarioBase;
import com.rlti.rh.contrato.repository.CargoRepository;
import com.rlti.rh.funcionario.service.SalarioBaseService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CargoApplicationService implements CargoService {

    private final CargoRepository cargoRepository;
    private final SalarioBaseService salarioBaseService;

    @Override
    public CargoIdResponse newCargo(CargoRequest request) {
        log.info("CargoApplicationService.newCargo");
        Cargo cargo = cargoRepository.saveCargo(new Cargo(request));
        List<SalarioBase> salarios = salarioBaseService.saveAll(request.salarios(), cargo);
        cargo.setSalarios(salarios);
        cargo = cargoRepository.saveCargo(cargo);
        return CargoIdResponse.builder().idCargo(cargo.getIdCargo()).build();
    }


    @Override
    public CargoResponse findByNomeCargo(String nomeCargo) {
        log.info("CargoApplicationService.findByNomeCargo");
        Cargo cargo = cargoRepository.findByNomeCargo(nomeCargo);
        return new CargoResponse(cargo);
    }

    @Override
    public List<CargoResponse> findAllCargos() {
        return cargoRepository.findAllCargos().stream().map(CargoResponse::new).toList();
    }


}
