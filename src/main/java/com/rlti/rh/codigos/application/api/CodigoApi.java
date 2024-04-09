package com.rlti.rh.codigos.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Código")
@RequestMapping("/v1/codigos")
public interface CodigoApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CodigoResponse newCodigo(@Valid @RequestBody CodigoRequest codigoRequest);

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    List<CodigoResponse> getAllCodigos();
}
