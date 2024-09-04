package com.rlti.rh.funcionario.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.rlti.rh.funcionario.application.request.SalarioBaseRequest;
import com.rlti.rh.funcionario.application.response.SalarioBaseIdResponse;

@Tag(name = "Salário Base", description = "API de Salário Base")
@RequestMapping("/v1/salario-base")
public interface SalarioBaseApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Salário base criado com sucesso")
    SalarioBaseIdResponse newSalarioBase(@Valid @RequestBody SalarioBaseRequest request);
}
