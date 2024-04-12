package com.rlti.rh.calculo.repository;

import com.rlti.rh.folha.domain.VencimentosFolha;

import java.util.List;

public interface VencimentosFolhaRepository {
    void saveAll(List<VencimentosFolha> vencimentos);
}
