package com.rlti.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import com.rlti.rh.funcionario.domain.SalarioBase;
import com.rlti.rh.funcionario.repository.SalarioBaseRepository;
import com.rlti.rh.handler.APIException;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class SalarioBaseInfraRepository implements SalarioBaseRepository {
    private final SalarioBaseJpaRepository salarioBaseJpaRepository;

    @Override
    public SalarioBase findById(Long idSalarioBase) {
        return salarioBaseJpaRepository.findById(idSalarioBase)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Salário Base não encontrado"));
    }

    @Override
    public SalarioBase saveSalarioBase(SalarioBase salarioBase) {
        return salarioBaseJpaRepository.save(salarioBase);
    }

    @Override
    public List<SalarioBase> saveAll(List<SalarioBase> salarios) {
        return salarioBaseJpaRepository.saveAll(salarios);
    }

}
