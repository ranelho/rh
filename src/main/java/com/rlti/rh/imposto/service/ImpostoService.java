package com.rlti.rh.imposto.service;

import com.rlti.rh.imposto.application.InssRequest;
import com.rlti.rh.imposto.application.InssResponse;
import com.rlti.rh.imposto.application.IrrfRequest;
import com.rlti.rh.imposto.application.IrrfResponse;

import java.time.LocalDate;
import java.util.List;

public interface ImpostoService {
    boolean criarInss(InssRequest inssRequest);
    boolean criarIrrf(IrrfRequest irrfRequest);
    InssResponse consultarInss(Long id);
    IrrfResponse consultarIrrf(Long id);
    List<InssResponse> consultarAllInss(LocalDate inicioVigencia, LocalDate fimVigencia);
}
