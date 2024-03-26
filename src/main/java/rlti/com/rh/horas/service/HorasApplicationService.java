package rlti.com.rh.horas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rlti.com.rh.funcionario.domain.Matricula;
import rlti.com.rh.funcionario.repository.MatriculaRepository;
import rlti.com.rh.horas.application.api.HorasRequest;
import rlti.com.rh.horas.application.api.HorasUpdateRequest;
import rlti.com.rh.horas.domain.HorasTrabalhadas;
import rlti.com.rh.horas.repository.HorasRepository;

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
