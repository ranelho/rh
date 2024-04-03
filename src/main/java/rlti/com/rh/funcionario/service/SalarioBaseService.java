package rlti.com.rh.funcionario.service;

import rlti.com.rh.funcionario.application.request.SalarioBaseRequest;
import rlti.com.rh.funcionario.application.response.SalarioBaseIdResponse;
import rlti.com.rh.funcionario.domain.SalarioBase;

public interface SalarioBaseService {
    SalarioBase findById(Long idSalarioBase);
    SalarioBaseIdResponse saveSalarioBase(SalarioBaseRequest salarioBaseRequest);
}
