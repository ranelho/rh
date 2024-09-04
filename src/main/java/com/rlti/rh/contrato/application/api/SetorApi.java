package com.rlti.rh.contrato.application.api;

import com.rlti.rh.contrato.application.response.SetorResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.rlti.rh.contrato.application.request.SetorRequest;
import com.rlti.rh.contrato.application.response.SetorIdReponse;

import java.util.List;

@Tag(name = "Setor", description = "API de Setor")
@RequestMapping("/v1/setores")
public interface SetorApi {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    SetorIdReponse newSetor(@Valid @RequestBody SetorRequest request);

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    List<SetorResponse> getAllSetores();
}