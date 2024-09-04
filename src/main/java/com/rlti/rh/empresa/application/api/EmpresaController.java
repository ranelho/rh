package com.rlti.rh.empresa.application.api;

import com.rlti.rh.empresa.application.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class EmpresaController implements EmpresaApi{
    private final EmpresaService empresaService;

    @Override
    public EmpresaIdResponse saveEmpresa(EmpresaRequest empresaRequest) {
        return empresaService.saveEmpresa(empresaRequest);
    }

    @Override
    public EmpresaResponse getByCnpj(String cnpj) {
        return empresaService.getByCnpj(cnpj);
    }

    @Override
    public void updateEmpresa(Long idEmpresa, EmpresaUpdateRequest empresaUpdateRequest) {
        empresaService.updateEmpresa(idEmpresa, empresaUpdateRequest);
    }
}
