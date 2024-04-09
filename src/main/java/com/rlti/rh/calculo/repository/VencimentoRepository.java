package com.rlti.rh.calculo.repository;

import com.rlti.rh.folha.domain.Vencimentos;

import java.util.List;

public interface VencimentoRepository {
    List<Vencimentos> saveAll(List<Vencimentos> vencimentosASalvar);
}
