package com.rlti.rh.contrato.application.api;

import com.rlti.rh.contrato.application.response.CargoDocResponse;
import com.rlti.rh.contrato.application.response.CargoResponse;
import com.rlti.rh.document.api.response.CargoDocumentResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.rlti.rh.contrato.application.request.CargoRequest;
import com.rlti.rh.contrato.application.response.CargoIdResponse;

import java.util.List;

@Tag(name = "Cargo", description = "API de Cargo")
@RequestMapping("/v1/cargo")
public interface CargoApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    CargoIdResponse newCargo(@Valid @RequestBody CargoRequest request);

    @GetMapping("/nome-cargo/{nomeCargo}")
    @ResponseStatus(code = HttpStatus.OK)
    CargoResponse findByNomeCargo(@PathVariable String nomeCargo);

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    List<CargoResponse> findAllCargos();

    @GetMapping("/document/{idCargo}")
    CargoDocResponse getCargoDocuments(@PathVariable Long idCargo);
}
