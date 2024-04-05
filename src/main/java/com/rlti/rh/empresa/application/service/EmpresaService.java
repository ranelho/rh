package com.rlti.rh.empresa.application.service;

import com.rlti.rh.empresa.application.api.*;

import java.util.List;

public interface EmpresaService {
    EmpresaResponse saveEmpresa(EmpresaRequest empresaRequest);
    List<EmpresaListResponse> getAllEmpresas();
    EmpresaDetalhadoResponse getOneEmpresa(Long idEmpresa);
    EmpresaDetalhadoResponseCnpj getByCnpj(String cnpj);
    void deleteEmpresa(Long idEmpresa);
    void updateEmpresa(Long idEmpresa, EmpresaUpdateRequest empresaUpdateRequest);
}
