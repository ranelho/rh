package com.rlti.rh.calculo.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.calculo.service.CalculoService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CaculoRestController implements CaculoApi {

    private final CalculoService calculoService;

    @Override
    public boolean efetuarCalculo(String mesCompetencia) {
        return calculoService.efetuarCalculo(mesCompetencia);
    }

    @Override
    public void atualizarStatus(String mesCompetencia, String numeroMatricula, boolean status) {
        calculoService.atualizarStatus(mesCompetencia, numeroMatricula, status);
    }

    @Override
    public void deleteFolha(String mesCompetencia, String numeroMatricula) {
        calculoService.deleteFolha(mesCompetencia, numeroMatricula);
    }
}
