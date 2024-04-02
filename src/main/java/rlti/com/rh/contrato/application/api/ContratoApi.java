package rlti.com.rh.contrato.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rlti.com.rh.contrato.application.request.ContratoRequest;
import rlti.com.rh.contrato.application.request.ContratoDesligamentoRequest;
import rlti.com.rh.contrato.application.response.ContratoIdResponse;

@Tag(name = "Contrato", description = "API de Contrato")
@RequestMapping("/v1/contratos")
public interface ContratoApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    ContratoIdResponse newContratoFuncionario(@Valid @RequestBody ContratoRequest request);

    @PatchMapping("/desligamento/{matricula}")
    @ResponseStatus(code = HttpStatus.OK)
    void desligamentoFuncionario(@PathVariable String matricula, @Valid @RequestBody ContratoDesligamentoRequest desligamentoRequest);

    @PatchMapping("/renovacao-contrato/{matricula}/{prazoTotal}")
    @ResponseStatus(code = HttpStatus.OK)
    void renovacaoContrato(@PathVariable String matricula, @PathVariable Integer prazoTotal);
}