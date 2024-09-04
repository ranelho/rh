package com.rlti.rh.calculo.repository;

import com.rlti.rh.folha.domain.Descontos;
import com.rlti.rh.folha.domain.FolhaMensal;

import java.util.List;

public interface DescontosRepository {
    List<Descontos> saveAll(List<Descontos> descontoInss);

    void save(Descontos descontoInss);
    void deleteByFolhaMensal(FolhaMensal folhaMensal);
}
