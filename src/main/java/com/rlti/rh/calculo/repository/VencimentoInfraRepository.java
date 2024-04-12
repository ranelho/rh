package com.rlti.rh.calculo.repository;

import com.rlti.rh.folha.domain.Vencimentos;
import com.rlti.rh.horas.domain.Horas;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VencimentoInfraRepository implements VencimentoRepository {
    private final VencimentoRepositoryJpa vencimentoRepositoryJpa;

    @Override
    public List<Vencimentos> saveAll(List<Vencimentos> vencimentosASalvar) {
        return vencimentoRepositoryJpa.saveAll(vencimentosASalvar);
    }

    @Override
    public void save(Vencimentos vencimento) {
        vencimentoRepositoryJpa.save(vencimento);
    }

    @Override
    public void deleteVencimentosByHoras(Horas horas) {
        vencimentoRepositoryJpa.deleteAll(horas.getVencimentos());
    }
}
