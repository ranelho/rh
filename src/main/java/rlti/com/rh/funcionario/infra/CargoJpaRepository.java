package rlti.com.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.funcionario.domain.Cargo;

public interface CargoJpaRepository extends JpaRepository<Cargo, Long> {
}
