package com.rlti.rh.calculo.application.api;

import com.rlti.rh.calculo.service.CalculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
