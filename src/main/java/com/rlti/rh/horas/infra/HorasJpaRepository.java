package com.rlti.rh.horas.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.horas.domain.HorasTrabalhadas;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HorasJpaRepository extends JpaRepository<HorasTrabalhadas, Long> {
    Optional<HorasTrabalhadas> findByMatriculaAndMesCompetencia(Matricula matricula, String mesCompetencia);
    List<HorasTrabalhadas> findAllByMesCompetencia(String competencia);
}
