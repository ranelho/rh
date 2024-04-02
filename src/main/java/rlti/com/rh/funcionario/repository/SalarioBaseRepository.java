package rlti.com.rh.funcionario.repository;

import rlti.com.rh.funcionario.domain.SalarioBase;

public interface SalarioBaseRepository {
    SalarioBase findById(Long idSalarioBase);
    SalarioBase saveSalarioBase(SalarioBase salarioBase);
}
