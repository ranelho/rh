package com.rlti.rh.funcionario.service;

import com.rlti.rh.funcionario.application.request.SalarioBaseRequest;
import com.rlti.rh.funcionario.application.response.SalarioBaseIdResponse;
import com.rlti.rh.funcionario.domain.SalarioBase;

public interface SalarioBaseService {
    SalarioBase findById(Long idSalarioBase);
    SalarioBaseIdResponse saveSalarioBase(SalarioBaseRequest salarioBaseRequest);
}
