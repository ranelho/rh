package com.rlti.rh.horas.repository;

import com.rlti.rh.codigos.domain.Codigo;

public interface CodigoRepository {
    Codigo findByCodigo(String codigo);
}
