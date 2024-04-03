package rlti.com.rh.funcionario.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import rlti.com.rh.funcionario.application.request.SalarioBaseRequest;
import rlti.com.rh.funcionario.application.response.SalarioBaseIdResponse;
import rlti.com.rh.funcionario.domain.SalarioBase;
import rlti.com.rh.funcionario.service.SalarioBaseService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SalarioBaseRestController implements SalarioBaseApi {

    private final SalarioBaseService salarioBaseService;

    @Override
    public SalarioBaseIdResponse newSalarioBase(SalarioBaseRequest request) {
        log.info("SalarioBaseRestController - novoSalarioBase");
        return salarioBaseService.saveSalarioBase(request);
    }
}
