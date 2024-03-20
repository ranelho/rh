package rlti.com.rh.funcionario.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import rlti.com.rh.funcionario.application.api.request.SalarioBaseRequest;
import rlti.com.rh.funcionario.application.api.response.SalarioBaseIdResponse;

@Tag(name = "Salário Base", description = "API de Salário Base")
@RequestMapping("/v1/salario-base")
public interface SalarioBaseApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Salário base criado com sucesso")
    SalarioBaseIdResponse novoSalarioBase(@Valid @RequestBody SalarioBaseRequest request);
}
