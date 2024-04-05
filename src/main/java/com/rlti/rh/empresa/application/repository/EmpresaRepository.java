package com.rlti.rh.empresa.application.repository;

import com.rlti.rh.empresa.domain.Empresa;

import java.util.List;
import java.util.UUID;

public interface EmpresaRepository {
    Empresa saveEmpresa(Empresa empresa);
    List<Empresa> getAllEmpresas();
    Empresa getOneEmpresa(Long idEmpresa);
    Empresa getByCnpj(String cnpj);
    void deleteEmpresa(Long idEmpresa);
}