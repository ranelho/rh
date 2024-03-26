package rlti.com.rh.horas.repository;

import rlti.com.rh.funcionario.domain.Matricula;
import rlti.com.rh.horas.domain.HorasTrabalhadas;

import java.util.Date;

public interface HorasRepository {
    void salvarHoras(HorasTrabalhadas horasTrabalhadas);
    HorasTrabalhadas findHorasByNumeroMatriculaAndMesReferencia(Matricula matricula, Date mesReferencia);
}
