package com.rlti.rh.empresa.application.service;


import com.rlti.rh.empresa.application.api.*;
import com.rlti.rh.empresa.application.repository.EmpresaRepository;
import com.rlti.rh.empresa.domain.Empresa;
import com.rlti.rh.empresa.validation.ValidaCpfouCnpj;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmpresaApplicationService implements EmpresaService{
    private final EmpresaRepository empresaRepository;

    @Override
    public EmpresaIdResponse saveEmpresa(EmpresaRequest empresaRequest) {
        log.info("[inicia] EmpresaApplicationService - saveEmpresa");
        Empresa empresa = empresaRepository.saveEmpresa(new Empresa(empresaRequest));
        log.info("[finaliza] EmpresaApplicationService - saveEmpresa");
        return EmpresaIdResponse.builder().idEmpresa(empresa.getIdEmpresa()).build();
    }

    @Override
    public EmpresaResponse getOneEmpresa(Long idEmpresa) {
        log.info("[inicia] EmpresaApplicationService - getOneEmpresa");
        Empresa empresa = empresaRepository.getOneEmpresa(idEmpresa);
        log.info("[finaliza] EmpresaApplicationService - getOneEmpresa");
        return new EmpresaResponse(empresa);
    }

    @Override
    public EmpresaResponse getByCnpj(String cnpj) {
        log.info("[inicia] EmpresaApplicationService - getByCnpj");
        ValidaCpfouCnpj.validateCpfOrCnpj(cnpj);
        Empresa empresa = empresaRepository.getByCnpj(cnpj);
        log.info("[finaliza] EmpresaApplicationService - getByCnpj");
        return new EmpresaResponse(empresa);
    }

    @Override
    public void deleteEmpresa(Long idEmpresa) {
        log.info("[inicia] EmpresaApplicationService - deleteEmpresa");
        empresaRepository.deleteEmpresa(empresaRepository.getOneEmpresa(idEmpresa).getIdEmpresa());
        log.info("[finaliza] EmpresaApplicationService - deleteEmpresa");
    }

    @Override
    public void updateEmpresa(Long idEmpresa, EmpresaUpdateRequest empresaUpdateRequest) {
        log.info("[inicia] EmpresaApplicationService - updateEmpresa");
        Empresa empresa = empresaRepository.getOneEmpresa(idEmpresa);
        empresa.altera(empresaUpdateRequest);
        empresaRepository.saveEmpresa(empresa);
        log.info("[finaliza] EmpresaApplicationService - updateEmpresa");
    }
}
