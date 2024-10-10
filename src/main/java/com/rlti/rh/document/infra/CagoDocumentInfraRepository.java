package com.rlti.rh.document.infra;

import com.rlti.rh.document.domain.DocumentCargo;
import com.rlti.rh.document.repository.CagoDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CagoDocumentInfraRepository implements CagoDocumentRepository {
    private final CagoDocumentJpaRepository cagoDocumentJpaRepository;

    @Override
    public void saveAll(List<DocumentCargo> documentCargos) {
        cagoDocumentJpaRepository.saveAll(documentCargos);
    }
}
