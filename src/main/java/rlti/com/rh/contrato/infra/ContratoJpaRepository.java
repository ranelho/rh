package rlti.com.rh.contrato.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.contrato.domain.Contrato;
import rlti.com.rh.funcionario.domain.Matricula;

import java.util.Optional;

public interface ContratoJpaRepository extends JpaRepository<Contrato, Long> {
    Optional<Contrato> findByMatricula(Matricula matricula);
}
