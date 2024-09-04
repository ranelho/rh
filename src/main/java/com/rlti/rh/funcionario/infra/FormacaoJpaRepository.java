package com.rlti.rh.funcionario.infra;

import com.rlti.rh.funcionario.domain.Formacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormacaoJpaRepository extends JpaRepository<Formacao, Long> {
}