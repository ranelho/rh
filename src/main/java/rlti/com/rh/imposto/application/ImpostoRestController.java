package rlti.com.rh.imposto.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import rlti.com.rh.imposto.service.ImpostoService;

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
}
