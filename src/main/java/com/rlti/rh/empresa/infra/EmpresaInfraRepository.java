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
import java.util.function.Supplier;

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
        return jpaRepository.findAll();
    }

    @Override
    public Empresa getOneEmpresa(Long idEmpresa) {
        return jpaRepository.findById(idEmpresa).orElseThrow(getApiExceptionSupplier());
    }

    @Override
    public Empresa getByCnpj(String cnpj) {
        return jpaRepository.findByCnpj(cnpj).orElseThrow(getApiExceptionSupplier());
    }

    @Override
    public void deleteEmpresa(Long idEmpresa) {
        jpaRepository.deleteById(idEmpresa);
    }

    private static Supplier<APIException> getApiExceptionSupplier() {
        return () -> APIException.build(HttpStatus.BAD_REQUEST, "Empresa não encontrada!");
    }
}