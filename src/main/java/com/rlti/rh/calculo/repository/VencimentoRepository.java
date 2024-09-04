package com.rlti.rh.calculo.repository;

import com.rlti.rh.folha.domain.Vencimentos;
import com.rlti.rh.horas.domain.Horas;

import java.util.List;

public interface VencimentoRepository {
    List<Vencimentos> saveAll(List<Vencimentos> vencimentosASalvar);
    void save(Vencimentos vencimento);
    void deleteVencimentosByHoras(Horas horas);
}
