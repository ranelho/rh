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
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Não localizado"));
    }

    @Override
    public List<HorasTrabalhadas> findAllHoras(String competencia) {
        return horasJpaRepository.findAllByMesCompetencia(competencia);
    }
}
