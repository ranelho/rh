package rlti.com.rh.contrato.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.contrato.domain.Setor;

public interface SetorJpaRepository extends JpaRepository<Setor, Long> {
}
