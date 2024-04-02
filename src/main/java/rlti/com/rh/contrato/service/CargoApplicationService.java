package rlti.com.rh.contrato.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.contrato.application.request.CargoRequest;
import rlti.com.rh.contrato.application.response.CargoIdResponse;
import rlti.com.rh.contrato.application.response.CargoResponse;
import rlti.com.rh.contrato.domain.Cargo;
import rlti.com.rh.funcionario.domain.SalarioBase;
import rlti.com.rh.contrato.repository.CargoRepository;
import rlti.com.rh.funcionario.service.SalarioBaseService;

@Service
@Slf4j
@RequiredArgsConstructor
public class CargoApplicationService implements CargoService {
    private final CargoRepository cargoRepository;
    private final SalarioBaseService salarioBaseService;

    @Override
    public CargoIdResponse newCargo(CargoRequest request) {
        log.info("CargoApplicationService.novoCargo");
        SalarioBase salarioBase = request.salarioBase().idSalarioBase() == null ?
                salarioBaseService.findById(salarioBaseService.saveSalarioBase(request.salarioBase()).getIdSalarioBase()) :
                salarioBaseService.findById(request.salarioBase().idSalarioBase());

        Cargo cargo = cargoRepository.saveCargo(new Cargo(request, salarioBase));
        return CargoIdResponse.builder().idCargo(cargo.getIdCargo()).build();
    }

    @Override
    public CargoResponse findByNomeCargo(String nomeCargo) {
        log.info("CargoApplicationService.findByNomeCargo");
        Cargo cargo = cargoRepository.findByNomeCargo(nomeCargo);
        return new CargoResponse(cargo);
    }
}
