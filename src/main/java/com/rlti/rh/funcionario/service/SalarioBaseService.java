package com.rlti.rh.funcionario.service;

import com.rlti.rh.contrato.domain.Cargo;
import com.rlti.rh.funcionario.application.api.request.SalarioBaseRequest;
import com.rlti.rh.funcionario.application.api.response.SalarioBaseIdResponse;
import com.rlti.rh.funcionario.domain.SalarioBase;

import java.util.List;

public interface SalarioBaseService {
    SalarioBase findById(Long idSalarioBase);
    SalarioBaseIdResponse saveSalarioBase(SalarioBaseRequest salarioBaseRequest);
    List<SalarioBase> saveAll(List<SalarioBaseRequest> salarios, Cargo cargo);
}
