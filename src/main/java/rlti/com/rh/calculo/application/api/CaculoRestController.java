package rlti.com.rh.calculo.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import rlti.com.rh.calculo.application.api.request.SimulacaoInssRequest;
import rlti.com.rh.calculo.application.api.response.SimulacaoInssResponse;
import rlti.com.rh.calculo.service.CalculoService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CaculoRestController implements CaculoApi {

    private final CalculoService calculoService;

    @Override
    public SimulacaoInssResponse simularCalculoInss(SimulacaoInssRequest request) {
        return calculoService.simularCalculoInss(request);
    }
}
