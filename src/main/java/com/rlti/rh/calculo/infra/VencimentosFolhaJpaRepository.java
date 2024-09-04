package com.rlti.rh.calculo.infra;

import com.rlti.rh.folha.domain.VencimentosFolha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VencimentosFolhaJpaRepository extends JpaRepository<VencimentosFolha, Long> {
}
