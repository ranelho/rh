package com.rlti.rh.document.infra;

import com.rlti.rh.document.domain.DocumentType;
import com.rlti.rh.document.repository.DocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DocumentTypeInfraRepository implements DocumentTypeRepository {

    private final DocumentTypeJpaRepository documentTypeJpaRepository;

    @Override
    public Optional<DocumentType> findById(Long idDocument) {
        return documentTypeJpaRepository.findById(idDocument);
    }

    @Override
    public List<DocumentType> findAll() {
        return documentTypeJpaRepository.findAll();
    }

    @Override
    public DocumentType save(DocumentType documentType) {
        return documentTypeJpaRepository.save(documentType);
    }
}
