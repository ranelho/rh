package com.rlti.rh.document.repository;

import com.rlti.rh.document.domain.DocumentType;

import java.util.List;
import java.util.Optional;

public interface DocumentTypeRepository {
    Optional<DocumentType> findById(Long idDocument);

    List<DocumentType> findAll();

    DocumentType save(DocumentType documentType);
}
