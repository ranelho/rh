package rlti.com.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.SalarioBase;
import rlti.com.rh.funcionario.repository.SalarioBaseRepository;
import rlti.com.rh.handler.APIException;

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

}
