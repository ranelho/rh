package com.rlti.rh.calculo.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.rlti.rh.calculo.application.api.request.SimulacaoInssRequest;
import com.rlti.rh.calculo.application.api.response.SimulacaoResponse;

@Tag(name = "Calculo")
@RequestMapping("/v1/simular-calculo-inss")
public interface CaculoApi {
    @PostMapping()
    @ResponseStatus(code = HttpStatus.OK)
    SimulacaoResponse simularCalculoInss(@RequestBody SimulacaoInssRequest request);
}
