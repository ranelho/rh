package com.rlti.rh.horas.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.handler.APIException;
import com.rlti.rh.horas.domain.HorasTrabalhadas;
import com.rlti.rh.horas.repository.HorasRepository;

import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

@Repository
@RequiredArgsConstructor
public class HorasInfraRepository implements HorasRepository {

    private final HorasJpaRepository horasJpaRepository;

    @Override
    public void salvarHoras(HorasTrabalhadas horasTrabalhadas) {
        horasJpaRepository.save(horasTrabalhadas);
    }

    @Override
    public HorasTrabalhadas findHorasByNumeroMatriculaAndMesReferencia(Matricula matricula, Date mesReferencia) {
        return  horasJpaRepository.findByMatriculaAndMesCompetencia(matricula, mesReferencia.toString())
                .orElseThrow(responseException());
    }

    @Override
    public List<HorasTrabalhadas> findAllHorasTrue(String competencia) {
        return horasJpaRepository.findAllByMesCompetenciaAndCompetenciaFechada(competencia, false);
    }

    @Override
    public HorasTrabalhadas findHorasByMesCompetenciaAndMatricula(String mesCompetencia, Matricula matricula) {
        return horasJpaRepository.findByMatriculaAndMesCompetencia(matricula, mesCompetencia)
                .orElseThrow(responseException());
    }

    private static Supplier<APIException> responseException() {
        return () -> APIException.build(HttpStatus.BAD_REQUEST, "Não localizado");
    }
}
