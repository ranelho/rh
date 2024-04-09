package com.rlti.rh.horas.infra;

import com.rlti.rh.codigos.domain.Codigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodigoJpaRepository extends JpaRepository<Codigo, Long>{
    Codigo findByCod(String codigo);
}
