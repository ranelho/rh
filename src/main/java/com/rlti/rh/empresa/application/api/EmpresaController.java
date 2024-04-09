package com.rlti.rh.empresa.application.api;

import com.rlti.rh.empresa.application.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class EmpresaController implements EmpresaApi{
    private final EmpresaService empresaService;

    @Override
    public EmpresaResponse saveEmpresa(EmpresaRequest empresaRequest) {
        log.info("[inicia] EmpresaController - saveEmpresa");
        EmpresaResponse empresaCriada = empresaService.saveEmpresa(empresaRequest);
        log.info("[finaliza] EmpresaController - saveEmpresa");
        return empresaCriada;
    }

    @Override
    public List<EmpresaListResponse> getAllEmpresas() {
        log.info("[inicia] EmpresaController - getAllEmpresas");
        List<EmpresaListResponse> empresas = empresaService.getAllEmpresas();
        log.info("[finaliza] EmpresaController - getAllEmpresas");
        return empresas;
    }

    @Override
    public EmpresaDetalhadoResponse getOneEmpresa(Long idEmpresa) {
        log.info("[inicia] EmpresaController - getOneEmpresa");
        EmpresaDetalhadoResponse empresaDetalhadoId = empresaService.getOneEmpresa(idEmpresa);
        log.info("[finaliza] EmpresaController - getOneEmpresa");
        return empresaDetalhadoId;
    }

    @Override
    public EmpresaDetalhadoResponseCnpj getByCnpj(String cnpj) {
        log.info("[inicia] EmpresaController - getByCnpj");
        EmpresaDetalhadoResponseCnpj empresaDetalhado = empresaService.getByCnpj(cnpj);
        log.info("[finaliza] EmpresaController - getByCnpj");
        return empresaDetalhado;
    }
    @Override
    public void deleteEmpresa(Long idEmpresa) {
        log.info("[inicia] EmpresaController - deleteEmpresa");
        log.info("[IdEmpresa] {}", idEmpresa);
        empresaService.deleteEmpresa(idEmpresa);
        log.info("[finaliza] EmpresaController - deleteEmpresa");
    }

    @Override
    public void updateEmpresa(Long idEmpresa, EmpresaUpdateRequest empresaUpdateRequest) {
        log.info("[inicia] EmpresaController - updateEmpresa");
        empresaService.updateEmpresa(idEmpresa, empresaUpdateRequest);
        log.info("[inicia] EmpresaController - updateEmpresa");
    }
}
