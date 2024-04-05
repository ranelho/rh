package com.rlti.rh.folha.service;

import com.rlti.rh.folha.application.ContrachequeRequest;
import com.rlti.rh.folha.application.ContrachequeResponse;
import com.rlti.rh.folha.domain.FolhaMensal;
import com.rlti.rh.folha.repository.FolhaMensalRepository;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContrachequeApplicationService implements ContrachequeService {
    private final FolhaMensalRepository folhaMensalRepository;
    private final MatriculaRepository matriculaRepository;

    @Override
    public ContrachequeResponse getContracheque(ContrachequeRequest request) {
        Matricula matricula = matriculaRepository.findByNumeroMatricula(request.matricula());
        FolhaMensal folhaMensal = folhaMensalRepository.findByMatriculaAndMesCompetencia(matricula.getNumeroMatricula(), request.mesAno());
        return new ContrachequeResponse(folhaMensal);
    }
}
