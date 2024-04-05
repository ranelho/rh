package com.rlti.rh.horas.service;

import com.rlti.rh.horas.application.api.HorasRequest;
import com.rlti.rh.horas.application.api.HorasUpdateRequest;

import java.util.Date;

public interface HorasService {
    void regristrarHoras(HorasRequest horasRequest);
    void atualizarHoras(String numeroMatricula, Date mesReferencia, HorasUpdateRequest horasRequest);
}
