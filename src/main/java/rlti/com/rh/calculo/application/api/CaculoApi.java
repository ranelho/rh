package rlti.com.rh.calculo.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rlti.com.rh.calculo.application.api.request.SimulacaoInssRequest;
import rlti.com.rh.calculo.application.api.response.SimulacaoInssResponse;

@RequestMapping("/v1/simular-calculo-inss")
public interface CaculoApi {
    @PostMapping()
    @ResponseStatus(code = HttpStatus.OK)
    SimulacaoInssResponse simularCalculoInss(@RequestBody SimulacaoInssRequest request);
}
