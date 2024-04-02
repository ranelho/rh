package rlti.com.rh.contrato.repository;

import rlti.com.rh.contrato.domain.Setor;

public interface SetorRepository {
    Setor findSetorById(Long idSetor);
    Setor saveSetor(Setor setor);
}
