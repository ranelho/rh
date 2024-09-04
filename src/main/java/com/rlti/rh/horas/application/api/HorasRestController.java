package com.rlti.rh.horas.application.api;

import com.rlti.rh.folha.application.api.VencimentosRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.horas.service.HorasService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HorasRestController implements HorasApi {

    private final HorasService horasService;

    @Override
    public void registrarHoras(String numeroMatricula, String mesCompetencia, HorasRequest horasRequest) {
        horasService.regristrarHoras(numeroMatricula, mesCompetencia, horasRequest);
    }

    @Override
    public void deletarHoras(String numeroMatricula, String mesReferencia) {
        horasService.deleHoras(numeroMatricula, mesReferencia);
    }

    @Override
    public void addVencimentos(String numeroMatricula, String mesReferencia, List<VencimentosRequest> vencimentos) {
        horasService.addVencimentos(numeroMatricula, mesReferencia, vencimentos);
    }
}
