package rlti.com.rh.contrato.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import rlti.com.rh.contrato.application.api.request.SetorRequest;
import rlti.com.rh.contrato.application.api.response.SetorIdReponse;

@Tag(name = "Setor", description = "API de Setor")
@RequestMapping("/v1/setores")
public interface SetorApi {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    SetorIdReponse criaSetor(@Valid @RequestBody SetorRequest request);
}
