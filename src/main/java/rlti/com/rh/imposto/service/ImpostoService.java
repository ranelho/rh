package rlti.com.rh.imposto.service;

import rlti.com.rh.imposto.application.InssRequest;
import rlti.com.rh.imposto.application.InssResponse;
import rlti.com.rh.imposto.application.IrrfRequest;
import rlti.com.rh.imposto.application.IrrfResponse;

import java.time.LocalDate;
import java.util.List;

public interface ImpostoService {
    boolean criarInss(InssRequest inssRequest);
    boolean criarIrrf(IrrfRequest irrfRequest);
    InssResponse consultarInss(Long id);
    IrrfResponse consultarIrrf(Long id);
    List<InssResponse> consultarAllInss(LocalDate inicioVigencia, LocalDate fimVigencia);
}
