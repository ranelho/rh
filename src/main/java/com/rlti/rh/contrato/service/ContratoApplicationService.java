package com.rlti.rh.contrato.service;

import com.rlti.rh.contrato.application.api.ContratoResponse;
import com.rlti.rh.contrato.application.request.ContratoDesligamentoRequest;
import com.rlti.rh.contrato.application.request.ContratoRequest;
import com.rlti.rh.contrato.application.response.ContratoIdResponse;
import com.rlti.rh.contrato.domain.Cargo;
import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.contrato.domain.Setor;
import com.rlti.rh.contrato.repository.CargoRepository;
import com.rlti.rh.contrato.repository.ContratoRepository;
import com.rlti.rh.contrato.repository.SetorRepository;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContratoApplicationService implements ContratoService {

    private final ContratoRepository contratoRepository;
    private final MatriculaRepository matriculaRepository;
    private final SetorRepository setorRepository;
    private final CargoRepository cargoRepository;

    @Override
    public ContratoIdResponse newContratoFuncionario(ContratoRequest request) {
        Matricula matricula = matriculaRepository.findByNumeroMatricula(request.matricula());
        Setor setor = setorRepository.findSetorById(request.idSetor());
        Cargo cargo = cargoRepository.findCargoById(request.idCargo());
        Contrato contrato = contratoRepository.saveContrato(new Contrato(matricula, setor, cargo, request));
        return ContratoIdResponse.builder().idContrato(contrato.getIdContrato()).build();
    }

    @Override
    public void desligamentoFuncionario(String numeroMatricula, ContratoDesligamentoRequest desligamentoRequest) {
        Contrato contrato = getContrato(numeroMatricula);
        contrato.desligamento(desligamentoRequest);
        contratoRepository.saveContrato(contrato);
    }

    private Contrato getContrato(String numeroMatricula) {
        return contratoRepository.findByMatricula(matriculaRepository.findByNumeroMatricula(numeroMatricula));
    }

    @Override
    public void renovacaoContrato(String matricula, Integer prazoTotal) {
        Contrato contrato = getContrato(matricula);
        contrato.renovacao(prazoTotal);
        contratoRepository.saveContrato(contrato);
    }

    @Override
    public ContratoResponse findContratoByMatricula(String matricula) {
        Contrato contrato = contratoRepository.findByMatricula(matriculaRepository.findByNumeroMatricula(matricula));
        return new ContratoResponse(contrato);
    }
}