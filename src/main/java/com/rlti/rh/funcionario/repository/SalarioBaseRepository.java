package com.rlti.rh.funcionario.repository;

import com.rlti.rh.funcionario.domain.SalarioBase;

import java.util.List;

public interface SalarioBaseRepository {
    SalarioBase findById(Long idSalarioBase);
    SalarioBase saveSalarioBase(SalarioBase salarioBase);
    List<SalarioBase> saveAll(List<SalarioBase> salarios);
}
