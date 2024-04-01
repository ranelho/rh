package rlti.com.rh.funcionario.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rlti.com.rh.funcionario.application.api.request.ContatoRequest;

@RequestMapping(value = "/v1/contatos")
public interface ContatoApi {

    @PostMapping("/{cpf}")
    @ResponseStatus(HttpStatus.CREATED)
    void addContatoParticipante(@PathVariable("cpf") String cpf, @Valid @RequestBody ContatoRequest contatoRequest);

    @PutMapping("/update/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    void updateContato(@PathVariable("cpf") String cpf, @Valid @RequestBody ContatoRequest contatoRequest);

}
