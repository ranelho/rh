package rlti.com.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.Cargo;
import rlti.com.rh.funcionario.repository.CargoRepository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CargoInfraRepository implements CargoRepository {
    private final CargoJpaRepository cargoJpaRepository;

    @Override
    public Cargo save(Cargo cargo) {
        return cargoJpaRepository.save(cargo);
    }
}
