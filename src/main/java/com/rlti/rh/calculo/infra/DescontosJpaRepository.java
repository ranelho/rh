package com.rlti.rh.calculo.infra;

import com.rlti.rh.folha.domain.Descontos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescontosJpaRepository extends JpaRepository<Descontos, Long> {
}
