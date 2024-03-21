package rlti.com.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.funcionario.domain.SalarioBase;

public interface SalarioBaseJpaRepository extends JpaRepository<SalarioBase, Long> {
}
