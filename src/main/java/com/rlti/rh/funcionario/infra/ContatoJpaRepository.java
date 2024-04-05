package com.rlti.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rlti.rh.funcionario.domain.Contato;

public interface ContatoJpaRepository extends JpaRepository<Contato, Long> {
}
