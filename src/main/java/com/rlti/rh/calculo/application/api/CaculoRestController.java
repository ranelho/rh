package com.rlti.rh.calculo.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.calculo.application.api.request.SimulacaoInssRequest;
import com.rlti.rh.calculo.application.api.response.SimulacaoResponse;
import com.rlti.rh.calculo.service.CalculoService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CaculoRestController implements CaculoApi {

    private final CalculoService calculoService;

    @Override
    public SimulacaoResponse simularCalculoInss(SimulacaoInssRequest request) {
        return calculoService.simularCalculoInss(request);
    }

    @Override
    public boolean efetuarCalculo(String mesCompetencia) {
        return calculoService.efetuarCalculo(mesCompetencia);
    }

    @Override
    public void atualizarStatus(String mesCompetencia, String numeroMatricula, boolean status) {
        calculoService.atualizarStatus(mesCompetencia, numeroMatricula, status);
    }
}
