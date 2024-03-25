package rlti.com.rh.contrato.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import rlti.com.rh.contrato.application.api.response.ContratoIdResponse;

@Tag(name = "Contrato", description = "API de Contrato")
@RequestMapping("/v1/contratos")
public interface ContratoApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    ContratoIdResponse novoContrato(@Valid @RequestBody ContratoRequest request);

}