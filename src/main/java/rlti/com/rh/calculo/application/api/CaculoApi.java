package rlti.com.rh.calculo.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rlti.com.rh.calculo.application.api.request.SimulacaoInssRequest;
import rlti.com.rh.calculo.application.api.response.SimulacaoInssResponse;

@Tag(name = "Calculo")
@RequestMapping("/v1/simular-calculo-inss")
public interface CaculoApi {
    @PostMapping()
    @ResponseStatus(code = HttpStatus.OK)
    SimulacaoInssResponse simularCalculoInss(@RequestBody SimulacaoInssRequest request);
}
