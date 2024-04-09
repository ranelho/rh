package com.rlti.rh.contrato.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.contrato.application.request.ContratoDesligamentoRequest;
import com.rlti.rh.contrato.application.request.ContratoRequest;
import com.rlti.rh.contrato.application.response.ContratoIdResponse;
import com.rlti.rh.contrato.service.ContratoService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ContratoRestController implements ContratoApi {
    private final ContratoService contratoService;

    @Override
    public ContratoIdResponse newContratoFuncionario(ContratoRequest request) {
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
}