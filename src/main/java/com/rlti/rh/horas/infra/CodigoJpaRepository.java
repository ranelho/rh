package com.rlti.rh.horas.infra;

import com.rlti.rh.codigos.domain.Codigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodigoJpaRepository extends JpaRepository<Codigo, Long>{
    Codigo findByCod(String codigo);
    Optional<Codigo> findByCodOrDescricao(String cod, String descricao);
}
