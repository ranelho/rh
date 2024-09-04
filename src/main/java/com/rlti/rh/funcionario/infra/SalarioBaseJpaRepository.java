package com.rlti.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rlti.rh.funcionario.domain.SalarioBase;

public interface SalarioBaseJpaRepository extends JpaRepository<SalarioBase, Long> {
}
