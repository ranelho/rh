package com.rlti.rh.document.infra;

import com.rlti.rh.document.domain.FileReference;
import com.rlti.rh.document.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DocumentoInfraRepository implements DocumentoRepository {

    private final DocumentoJpaRepository documentoJpaRepository;

    @Override
    public void save(FileReference fileReference) {
        documentoJpaRepository.save(fileReference);
    }
}
