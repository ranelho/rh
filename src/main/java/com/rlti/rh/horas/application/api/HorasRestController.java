package com.rlti.rh.horas.application.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.horas.service.HorasService;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class HorasRestController implements HorasApi {

    private final HorasService horasService;

    @Override
    public void registrarHoras(HorasRequest horasRequest) {
        horasService.regristrarHoras(horasRequest);
    }

    @Override
    public void atualizarHoras(String numeroMatricula, Date mesReferencia, HorasUpdateRequest horasRequest) {
        horasService.atualizarHoras(numeroMatricula, mesReferencia, horasRequest);
    }
}
