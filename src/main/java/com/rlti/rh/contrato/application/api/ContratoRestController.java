package com.rlti.rh.contrato.application.api;

import com.rlti.rh.contrato.application.response.ContratosAtivosResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.contrato.application.request.ContratoDesligamentoRequest;
import com.rlti.rh.contrato.application.request.ContratoRequest;
import com.rlti.rh.contrato.application.response.ContratoIdResponse;
import com.rlti.rh.contrato.service.ContratoService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ContratoRestController implements ContratoApi {
    private final ContratoService contratoService;

    @Override
    public ContratoResponse newContratoFuncionario(ContratoRequest request) {
        return contratoService.newContratoFuncionario(request);
    }

    @Override
    public void desligamentoFuncionario(String matricula, ContratoDesligamentoRequest desligamentoRequest) {
        contratoService.desligamentoFuncionario(matricula, desligamentoRequest);
    }

    @Override
    public void renovacaoContrato(String matricula, Integer prazoTotal) {
        contratoService.renovacaoContrato(matricula, prazoTotal);
    }

    @Override
    public com.rlti.rh.contrato.application.api.ContratoResponse findContratoByMatricula(String matricula) {
        return contratoService.findContratoByMatricula(matricula);
    }

    @Override
    public void addValeTransporte(String matricula, Integer quantidade, Long idAuxilioTransporte) {
        contratoService.addValeTransporte(matricula, quantidade, idAuxilioTransporte);
    }

    @Override
    public List<ContratosAtivosResponse> findContratosAtivos() {
        return contratoService.findContratosAtivos();
    }
}
