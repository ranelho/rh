package com.rlti.rh.contrato.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.rlti.rh.contrato.application.request.CargoRequest;
import com.rlti.rh.contrato.application.response.CargoIdResponse;
import com.rlti.rh.contrato.application.response.CargoResponse;

@Tag(name = "Cargo", description = "API de Cargo")
@RequestMapping("/v1/cargo")
public interface CargoApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    CargoIdResponse newCargo(@Valid @RequestBody CargoRequest request);

    @GetMapping("/nome-cargo/{nomeCargo}")
    @ResponseStatus(code = HttpStatus.OK)
    CargoResponse findByNomeCargo(@PathVariable String nomeCargo);
}
