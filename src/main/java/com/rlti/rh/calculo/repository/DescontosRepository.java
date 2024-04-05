package com.rlti.rh.calculo.repository;

import com.rlti.rh.folha.domain.Descontos;

import java.util.List;

public interface DescontosRepository {
    List<Descontos> saveAll(List<Descontos> descontoInss);

    void save(Descontos descontoInss);
}
