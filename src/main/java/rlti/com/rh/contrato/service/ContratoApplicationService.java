package rlti.com.rh.contrato.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.contrato.application.request.ContratoDesligamentoRequest;
import rlti.com.rh.contrato.application.request.ContratoRequest;
import rlti.com.rh.contrato.application.response.ContratoIdResponse;
import rlti.com.rh.contrato.domain.Cargo;
import rlti.com.rh.contrato.domain.Contrato;
import rlti.com.rh.contrato.domain.Setor;
import rlti.com.rh.contrato.repository.CargoRepository;
import rlti.com.rh.contrato.repository.ContratoRepository;
import rlti.com.rh.contrato.repository.SetorRepository;
import rlti.com.rh.funcionario.domain.Matricula;
import rlti.com.rh.funcionario.repository.MatriculaRepository;

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
        return ContratoIdResponse.builder().idContrato(contrato.getIdContato()).build();
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
}