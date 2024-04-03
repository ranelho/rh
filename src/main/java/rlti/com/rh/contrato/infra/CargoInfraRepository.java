package rlti.com.rh.contrato.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.contrato.domain.Cargo;
import rlti.com.rh.contrato.repository.CargoRepository;
import rlti.com.rh.handler.APIException;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CargoInfraRepository implements CargoRepository {
    private final CargoJpaRepository cargoJpaRepository;

    @Override
    public Cargo saveCargo(Cargo cargo) {
        log.info("CargoInfraRepository.save");
        return cargoJpaRepository.save(cargo);
    }

    @Override
    public Cargo findByNomeCargo(String nomeCargo) {
        log.info("CargoInfraRepository.findByNomeCargo");
        return cargoJpaRepository.findByNomeCargo(nomeCargo)
                .orElseThrow(()-> APIException.build(HttpStatus.BAD_REQUEST, "Cargo não encontrado"));
    }

    @Override
    public Cargo findCargoById(Long idCargo) {
        return cargoJpaRepository.findById(idCargo)
                .orElseThrow(()-> APIException.build(HttpStatus.BAD_REQUEST, "Cargo não encontrado"));
    }

}
