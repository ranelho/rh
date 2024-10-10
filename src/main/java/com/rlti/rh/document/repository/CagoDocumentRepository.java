package com.rlti.rh.document.repository;

import com.rlti.rh.document.domain.DocumentCargo;

import java.util.List;

public interface CagoDocumentRepository {
    void saveAll(List<DocumentCargo> documentCargos);
}
