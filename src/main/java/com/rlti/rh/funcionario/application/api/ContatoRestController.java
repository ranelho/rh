package com.rlti.rh.funcionario.application.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.funcionario.application.api.request.ContatoRequest;
import com.rlti.rh.funcionario.service.ContatoService;

@RestController
@RequiredArgsConstructor
public class ContatoRestController implements ContatoApi {

    private final ContatoService contatoService;

    @Override
    public void addContatoFuncionario(String cpf, ContatoRequest contatoRequest) {
        contatoService.addContatoFuncionario(cpf, contatoRequest);
    }

    @Override
    public void updateContato(String cpf, ContatoRequest contatoRequest) {
        contatoService.updateContato(cpf, contatoRequest);
    }
}
