package com.rlti.rh.calculo.infra;

import com.rlti.rh.calculo.repository.VencimentosFolhaRepository;
import com.rlti.rh.folha.domain.VencimentosFolha;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VencimentosFolhaInfraRepository implements VencimentosFolhaRepository {
    private final VencimentosFolhaJpaRepository vencimentosFolhaJpaRepository;

    @Override
    public void saveAll(List<VencimentosFolha> vencimentos) {
        vencimentosFolhaJpaRepository.saveAll(vencimentos);
    }
}
