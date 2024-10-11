package com.rlti.rh.funcionario.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.rlti.rh.funcionario.application.api.request.ContatoRequest;

@Tag(name = "Contatos" , description = "API de Contatos")
@RequestMapping(value = "/v1/contatos")
public interface ContatoApi {

    @PostMapping("/{cpf}")
    @ResponseStatus(HttpStatus.CREATED)
    void addContatoFuncionario(@PathVariable("cpf") String cpf, @Valid @RequestBody ContatoRequest contatoRequest);

    @PutMapping("/update/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    void updateContato(@PathVariable("cpf") String cpf, @Valid @RequestBody ContatoRequest contatoRequest);
}