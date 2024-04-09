package com.rlti.rh.horas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import com.rlti.rh.horas.application.api.HorasRequest;
import com.rlti.rh.horas.application.api.HorasUpdateRequest;
import com.rlti.rh.horas.domain.HorasTrabalhadas;
import com.rlti.rh.horas.repository.HorasRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class HorasApplicationService implements HorasService {

    private final HorasRepository horasRepository;
    private final MatriculaRepository matriculaRepository;

    @Override
    public void regristrarHoras(HorasRequest horasRequest) {
        horasRepository.salvarHoras(new HorasTrabalhadas(
                horasRequest, matriculaRepository.findByNumeroMatricula(horasRequest.numeroMatricula())))
        ;
    }

    @Override
    public void atualizarHoras(String numeroMatricula, Date mesReferencia, HorasUpdateRequest horasRequest) {
        Matricula matricula  = matriculaRepository.findByNumeroMatricula(numeroMatricula);
        HorasTrabalhadas horasTrabalhadas = horasRepository.findHorasByNumeroMatriculaAndMesReferencia(matricula, mesReferencia);
        horasTrabalhadas.updateHoras(horasRequest);
        horasRepository.salvarHoras(horasTrabalhadas);
    }
}
