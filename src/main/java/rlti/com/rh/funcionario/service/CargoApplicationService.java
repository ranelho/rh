package rlti.com.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.funcionario.application.api.request.CargoRequest;
import rlti.com.rh.funcionario.application.api.response.CargoIdResponse;
import rlti.com.rh.funcionario.application.api.response.CargoResponse;
import rlti.com.rh.funcionario.domain.Cargo;
import rlti.com.rh.funcionario.domain.SalarioBase;
import rlti.com.rh.funcionario.repository.CargoRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CargoApplicationService implements CargoService {
    private final CargoRepository cargoRepository;
    private final SalarioBaseService salarioBaseService;

    @Override
    public CargoIdResponse novoCargo(CargoRequest request) {
        log.info("CargoApplicationService.novoCargo");
        SalarioBase salarioBase = request.salarioBase().idSalarioBase() == null ?
                salarioBaseService.novoSalarioBase(request.salarioBase()) :
                salarioBaseService.findById(request.salarioBase().idSalarioBase());

        Cargo cargo = cargoRepository.save(new Cargo(request, salarioBase));
        return CargoIdResponse.builder().idCargo(cargo.getIdCargo()).build();
    }

    @Override
    public CargoResponse findByNomeCargo(String nomeCargo) {
        log.info("CargoApplicationService.findByNomeCargo");
        Cargo cargo = cargoRepository.findByNomeCargo(nomeCargo);
        return new CargoResponse(cargo);
    }
}
