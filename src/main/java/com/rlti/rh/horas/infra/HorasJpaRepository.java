package com.rlti.rh.horas.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.horas.domain.Horas;

import java.util.List;
import java.util.Optional;

public interface HorasJpaRepository extends JpaRepository<Horas, Long> {
    Optional<Horas> findByMatriculaAndMesCompetencia(Matricula matricula, String mesCompetencia);
    List<Horas> findAllByMesCompetenciaAndCompetenciaFechada(String competencia, boolean b);
}
