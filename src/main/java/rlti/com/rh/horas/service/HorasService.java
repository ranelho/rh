package rlti.com.rh.horas.service;

import rlti.com.rh.horas.application.api.HorasRequest;
import rlti.com.rh.horas.application.api.HorasUpdateRequest;

import java.util.Date;

public interface HorasService {
    void regristrarHoras(HorasRequest horasRequest);
    void atualizarHoras(String numeroMatricula, Date mesReferencia, HorasUpdateRequest horasRequest);
}
