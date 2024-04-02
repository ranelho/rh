package rlti.com.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.funcionario.application.request.SalarioBaseRequest;
import rlti.com.rh.funcionario.application.response.SalarioBaseIdResponse;
import rlti.com.rh.funcionario.domain.SalarioBase;
import rlti.com.rh.funcionario.repository.SalarioBaseRepository;

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
}
