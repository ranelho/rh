package com.rlti.rh.contrato.repository;

import com.rlti.rh.contrato.domain.Setor;

public interface SetorRepository {
    Setor findSetorById(Long idSetor);
    Setor saveSetor(Setor setor);
}
