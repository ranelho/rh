package com.rlti.rh.folha.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Contracheque")
@RequestMapping("v1/contracheque")
public interface ContrachequeApi {

    @PostMapping("/funcionario")
    @ResponseStatus(HttpStatus.OK)
    ContrachequeResponse getContracheque(@Valid @RequestBody ContrachequeRequest request);
}
