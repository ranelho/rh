package rlti.com.rh.contrato.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.contrato.domain.Cargo;

import java.util.Optional;

public interface CargoJpaRepository extends JpaRepository<Cargo, Long> {
    Optional<Cargo> findByNomeCargo(String nomeCargo);
}
