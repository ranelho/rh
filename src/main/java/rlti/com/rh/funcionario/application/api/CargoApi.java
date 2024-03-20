package rlti.com.rh.funcionario.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import rlti.com.rh.funcionario.application.api.request.CargoRequest;
import rlti.com.rh.funcionario.application.api.response.CargoIdResponse;

@RequestMapping("/v1/cargo")
public interface CargoApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Cargo criado com sucesso")
    CargoIdResponse novoCargo(@Valid @RequestBody CargoRequest request);
}
