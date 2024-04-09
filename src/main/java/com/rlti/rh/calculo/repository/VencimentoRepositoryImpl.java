package com.rlti.rh.calculo.repository;

import com.rlti.rh.folha.domain.Vencimentos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VencimentoRepositoryImpl implements VencimentoRepository {
    private final VencimentoRepositoryJpa vencimentoRepositoryJpa;

    @Override
    public List<Vencimentos> saveAll(List<Vencimentos> vencimentosASalvar) {
        return vencimentoRepositoryJpa.saveAll(vencimentosASalvar);
    }
}
