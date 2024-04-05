package com.rlti.rh.contrato.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.funcionario.domain.Matricula;

import java.util.Optional;

public interface ContratoJpaRepository extends JpaRepository<Contrato, Long> {
    Optional<Contrato> findByMatricula(Matricula matricula);
}
