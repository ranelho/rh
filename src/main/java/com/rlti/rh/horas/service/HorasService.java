package com.rlti.rh.horas.service;

import com.rlti.rh.folha.application.api.VencimentosRequest;
import com.rlti.rh.horas.application.api.HorasRequest;

import java.util.List;

public interface HorasService {
    void deleHoras(String numeroMatricula, String mesReferencia);
    void regristrarHoras(String numeroMatricula, String mesCompetencia, HorasRequest horasRequest);
    void addVencimentos(String numeroMatricula, String mesReferencia, List<VencimentosRequest> vencimentos);
}
