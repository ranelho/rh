package rlti.com.rh.calculo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rlti.com.rh.calculo.request.SimulacaoInssRequest;
import rlti.com.rh.calculo.response.SimulacaoInssResponse;

@RequestMapping("/v1/simular-calculo-inss")
public interface CaculoApi {
    @PostMapping()
    @ResponseStatus(code = HttpStatus.OK)
    SimulacaoInssResponse simularCalculoInss(@RequestBody SimulacaoInssRequest request);
}
