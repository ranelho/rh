package rlti.com.rh.funcionario.service;

import rlti.com.rh.funcionario.application.api.request.SalarioBaseRequest;
import rlti.com.rh.funcionario.domain.SalarioBase;

public interface SalarioBaseService {
    SalarioBase findById(Long idSalarioBase);
    SalarioBase novoSalarioBase(SalarioBaseRequest salarioBaseRequest);
}
