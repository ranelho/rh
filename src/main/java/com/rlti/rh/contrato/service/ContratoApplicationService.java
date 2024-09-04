package com.rlti.rh.contrato.service;

import com.rlti.rh.contrato.application.api.ContratoResponse;
import com.rlti.rh.contrato.application.request.ContratoDesligamentoRequest;
import com.rlti.rh.contrato.application.request.ContratoRequest;
import com.rlti.rh.contrato.application.response.ContratosAtivosResponse;
import com.rlti.rh.contrato.domain.AuxilioTransporte;
import com.rlti.rh.contrato.domain.Cargo;
import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.contrato.domain.Setor;
import com.rlti.rh.contrato.repository.AuxilioTransporteRepository;
import com.rlti.rh.contrato.repository.CargoRepository;
import com.rlti.rh.contrato.repository.ContratoRepository;
import com.rlti.rh.contrato.repository.SetorRepository;
import com.rlti.rh.funcionario.domain.Funcionario;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.funcionario.repository.FuncionarioRepository;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import com.rlti.rh.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContratoApplicationService implements ContratoService {

    private final ContratoRepository contratoRepository;
    private final MatriculaRepository matriculaRepository;
    private final SetorRepository setorRepository;
    private final CargoRepository cargoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final Utils utils;
    private  final AuxilioTransporteRepository auxilioTransporteRepository;

    @Override
    public ContratoResponse newContratoFuncionario(ContratoRequest request) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByCpf(request.cpf());
        Matricula matricula = matriculaRepository.novaMatricula(new Matricula(funcionario, utils.gerarMatricula()));
        Setor setor = setorRepository.findSetorById(request.idSetor());
        Cargo cargo = cargoRepository.findCargoById(request.idCargo());
        Contrato contrato = contratoRepository.saveContrato(new Contrato(matricula, setor, cargo, request));
        return new ContratoResponse(contrato);
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
        return new com.rlti.rh.contrato.application.api.ContratoResponse(contrato);
    }

    @Override
    public void addValeTransporte(String matricula, Integer quantidade, Long idAuxilioTransporte) {
        Contrato contrato = contratoRepository.findByMatricula(matriculaRepository.findByNumeroMatricula(matricula));
        AuxilioTransporte auxilioTransporte = auxilioTransporteRepository.findById(idAuxilioTransporte);
        contrato.addValeTransporte(quantidade, auxilioTransporte);
        contratoRepository.saveContrato(contrato);
    }

    @Override
    public List<ContratosAtivosResponse> findContratosAtivos() {
        List<Contrato> contratos = contratoRepository.findContratosAtivos();
        return ContratosAtivosResponse.convert(contratos);
    }
}