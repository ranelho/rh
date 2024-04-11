package com.rlti.rh.horas.repository;

import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.horas.domain.Horas;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HorasRepository {
    Horas salvarHoras(Horas horas);
    Horas findHorasByNumeroMatriculaAndMesReferencia(Matricula matricula, String mesReferencia);
    List<Horas> findAllHorasTrue(String competencia);
    Horas findHorasByMesCompetenciaAndMatricula(String mesCompetencia, Matricula matricula);
    void deletarHoras(Horas horas);

    Optional<Horas> findHorasByNumeroMatriculaAndMesReferencia2(Matricula matricula, String mesCompetencia);
}
