package com.rlti.rh.empresa.infra;

import com.rlti.rh.empresa.application.repository.EmpresaRepository;
import com.rlti.rh.empresa.domain.Empresa;
import com.rlti.rh.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Log4j2
@RequiredArgsConstructor
public class EmpresaInfraRepository implements EmpresaRepository {
    private final EmpresaSpringDataJPARepository jpaRepository;

    @Override
    public Empresa saveEmpresa(Empresa empresa) {
        log.info("[inicia] EmpresaInfraRepository - saveEmpresa");
        try {
            jpaRepository.save(empresa);
        } catch (DataIntegrityViolationException e) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Existem dados duplicados", e);
        }
        log.info("[finaliza] EmpresaInfraRepository - saveEmpresa");
        return empresa;
    }
    @Override
    public List<Empresa> getAllEmpresas() {
        log.info("[inicia] EmpresaInfraRepository - getAllEmpresas");
        List<Empresa> todasEmpresas = jpaRepository.findAll();
        log.info("[finaliza] EmpresaInfraRepository - getAllEmpresas");
        return todasEmpresas;
    }
    @Override
    public Empresa getOneEmpresa(Long idEmpresa) {
        log.info("[inicia] EmpresaInfraRepository - getOneEmpresa");
        Empresa empresa = jpaRepository.findById(idEmpresa)
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Empresa não encontrado"));
        log.info("[finaliza] EmpresaInfraRepository - getOneEmpresa");
        return empresa;
    }
    @Override
    public Empresa getByCnpj(String cnpj) {
        log.info("[inicia] EmpresaInfraRepository - getByCnpj");
        Empresa empresa = jpaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST,
                "Empresa não encontrada!"));
        log.info("[finaliza] EmpresaInfraRepository - getByCnpj");
        return empresa;
    }
    @Override
    public void deleteEmpresa(Long idEmpresa) {
        log.info("[inicia] EmpresaInfraRepository - deleteEmpresa");
        jpaRepository.deleteById(idEmpresa);
        log.info("[finaliza] EmpresaInfraRepository - deleteEmpresa");
    }
}