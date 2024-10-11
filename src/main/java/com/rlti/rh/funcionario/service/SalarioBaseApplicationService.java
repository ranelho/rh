package com.rlti.rh.funcionario.service;

import com.rlti.rh.contrato.domain.Cargo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.rlti.rh.funcionario.application.api.request.SalarioBaseRequest;
import com.rlti.rh.funcionario.application.api.response.SalarioBaseIdResponse;
import com.rlti.rh.funcionario.domain.SalarioBase;
import com.rlti.rh.funcionario.repository.SalarioBaseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalarioBaseApplicationService implements SalarioBaseService {
    private final SalarioBaseRepository salarioBaseRepository;

    @Override
    public SalarioBase findById(Long idSalarioBase) {
        log.info("SalarioBaseApplicationService.findById {}", idSalarioBase);
        return salarioBaseRepository.findById(idSalarioBase);
    }

    @Override
    public SalarioBaseIdResponse saveSalarioBase(SalarioBaseRequest salarioBaseRequest) {
        log.info("SalarioBaseApplicationService.novoSalarioBase");
        SalarioBase salarioBase = salarioBaseRepository.saveSalarioBase(new SalarioBase(salarioBaseRequest));
        return SalarioBaseIdResponse.builder().idSalarioBase(salarioBase.getIdSalarioBase()).build();
    }

    @Override
    public List<SalarioBase> saveAll(List<SalarioBaseRequest> salariosRequest, Cargo cargo) {
        List<SalarioBase> salarios = new ArrayList<>();
        for (SalarioBaseRequest request : salariosRequest) {
            salarios.add(new SalarioBase(request, cargo));
        }
        return salarioBaseRepository.saveAll(salarios);
    }
}
