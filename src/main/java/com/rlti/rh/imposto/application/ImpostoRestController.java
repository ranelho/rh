package com.rlti.rh.imposto.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.imposto.service.ImpostoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ImpostoRestController implements ImpostoApi {

    private final ImpostoService impostoService;

    @Override
    public boolean criarInss(InssRequest inssRequest) {
        return impostoService.criarInss(inssRequest);
    }

    @Override
    public boolean criarIrrf(IrrfRequest irrfRequest) {
        return impostoService.criarIrrf(irrfRequest);
    }

    @Override
    public InssResponse consultarInss(Long id) {
        return impostoService.consultarInss(id);
    }

    @Override
    public IrrfResponse consultarIrrf(Long id) {
        return impostoService.consultarIrrf(id);
    }

    @Override
    public List<InssResponse> consultarAllInss(LocalDate inicioVigencia, LocalDate fimVigencia) {
        return impostoService.consultarAllInss(inicioVigencia, fimVigencia);
    }
}
