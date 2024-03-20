package rlti.com.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.funcionario.domain.Cargo;

import java.util.Optional;

public interface CargoJpaRepository extends JpaRepository<Cargo, Long> {
    Optional<Cargo> findByNomeCargo(String nomeCargo);
}
