package com.rlti.rh.document.repository;

import com.rlti.rh.document.domain.FileReference;

public interface DocumentoRepository {
    void save(FileReference fileReference);
}
