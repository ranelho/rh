package com.rlti.rh.contrato.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rlti.rh.contrato.domain.Setor;

public interface SetorJpaRepository extends JpaRepository<Setor, Long> {
}
