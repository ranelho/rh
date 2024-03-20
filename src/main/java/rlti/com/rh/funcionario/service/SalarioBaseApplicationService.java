package rlti.com.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.funcionario.application.api.request.SalarioBaseRequest;
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
    public SalarioBase novoSalarioBase(SalarioBaseRequest salarioBaseRequest) {
        log.info("SalarioBaseApplicationService.novoSalarioBase");
        return salarioBaseRepository.save(new SalarioBase(salarioBaseRequest));
    }
}
