package com.rlti.rh.horas.repository;

import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.horas.domain.HorasTrabalhadas;

import java.util.Date;
import java.util.List;

public interface HorasRepository {
    void salvarHoras(HorasTrabalhadas horasTrabalhadas);
    HorasTrabalhadas findHorasByNumeroMatriculaAndMesReferencia(Matricula matricula, Date mesReferencia);
    List<HorasTrabalhadas> findAllHorasTrue(String competencia);
}
