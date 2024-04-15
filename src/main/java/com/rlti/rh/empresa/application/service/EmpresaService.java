package com.rlti.rh.empresa.application.service;

import com.rlti.rh.empresa.application.api.EmpresaIdResponse;
import com.rlti.rh.empresa.application.api.EmpresaRequest;
import com.rlti.rh.empresa.application.api.EmpresaResponse;
import com.rlti.rh.empresa.application.api.EmpresaUpdateRequest;

public interface EmpresaService {
    EmpresaIdResponse saveEmpresa(EmpresaRequest empresaRequest);
    EmpresaResponse getOneEmpresa(Long idEmpresa);
    EmpresaResponse getByCnpj(String cnpj);
    void deleteEmpresa(Long idEmpresa);
    void updateEmpresa(Long idEmpresa, EmpresaUpdateRequest empresaUpdateRequest);
}
