package com.rlti.rh.funcionario.repository;

import com.rlti.rh.funcionario.domain.SalarioBase;

public interface SalarioBaseRepository {
    SalarioBase findById(Long idSalarioBase);
    SalarioBase saveSalarioBase(SalarioBase salarioBase);
}
