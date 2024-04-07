package com.rlti.rh.funcionario.infra;

import com.rlti.rh.funcionario.domain.ContaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaPagamentoJpaRepository extends JpaRepository<ContaPagamento, Long> {
}
