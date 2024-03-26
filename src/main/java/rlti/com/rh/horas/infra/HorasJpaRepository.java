package rlti.com.rh.horas.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.funcionario.domain.Matricula;
import rlti.com.rh.horas.domain.HorasTrabalhadas;

import java.util.Date;
import java.util.Optional;

public interface HorasJpaRepository extends JpaRepository<HorasTrabalhadas, Long> {
    Optional<HorasTrabalhadas> findByMatriculaAndMesReferencia(Matricula matricula, Date mesReferencia);
}
