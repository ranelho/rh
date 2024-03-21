package rlti.com.rh.funcionario.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rlti.com.rh.funcionario.application.api.request.CargoRequest;
import rlti.com.rh.funcionario.application.api.response.CargoIdResponse;
import rlti.com.rh.funcionario.application.api.response.CargoResponse;

@Tag(name = "Cargo", description = "API de Cargo")
@RequestMapping("/v1/cargo")
public interface CargoApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Cargo criado com sucesso")
    CargoIdResponse novoCargo(@Valid @RequestBody CargoRequest request);

    @GetMapping("/nome-cargo/{nomeCargo}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Cargo encontrado com sucesso")
    CargoResponse findByNomeCargo(@PathVariable String nomeCargo);
}
