package rlti.com.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.Cargo;
import rlti.com.rh.funcionario.repository.CargoRepository;
import rlti.com.rh.handler.APIException;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CargoInfraRepository implements CargoRepository {
    private final CargoJpaRepository cargoJpaRepository;

    @Override
    public Cargo save(Cargo cargo) {
        log.info("CargoInfraRepository.save");
        return cargoJpaRepository.save(cargo);
    }

    @Override
    public Cargo findByNomeCargo(String nomeCargo) {
        log.info("CargoInfraRepository.findByNomeCargo");
        return cargoJpaRepository.findByNomeCargo(nomeCargo)
                .orElseThrow(()-> APIException.build(HttpStatus.BAD_REQUEST, "Cargo não encontrado"));
    }

}
