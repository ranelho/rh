package com.rlti.rh.horas.repository;

import com.rlti.rh.codigos.domain.Codigo;

import java.util.List;
import java.util.Optional;

public interface CodigoRepository {
    Codigo findByCodigo(String codigo);
    Codigo save(Codigo codigo);
    List<Codigo> findAll();
    Optional<Codigo> findByCodigoOrDescricao(String cod, String descricao);
}
