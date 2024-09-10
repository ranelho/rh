package com.rlti.rh.document.repository;

import com.rlti.rh.document.domain.DocumentType;
import com.rlti.rh.document.domain.FileReference;
import com.rlti.rh.funcionario.domain.Matricula;

import java.util.List;

public interface DocumentoRepository {
    void save(FileReference fileReference);

    List<FileReference> findByMatricula(Matricula matricula);

    void deleteByKey(String filePath);

    DocumentType salvar(DocumentType documentType);
}
