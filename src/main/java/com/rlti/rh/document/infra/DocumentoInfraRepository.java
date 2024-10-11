package com.rlti.rh.document.infra;

import com.rlti.rh.document.domain.DocumentType;
import com.rlti.rh.document.domain.FileReference;
import com.rlti.rh.document.repository.DocumentoRepository;
import com.rlti.rh.funcionario.domain.Matricula;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DocumentoInfraRepository implements DocumentoRepository {

    private final DocumentoJpaRepository documentoJpaRepository;
    private final DocumentTypeJpaRepository documentTypeJpaRepository;

    @Override
    public void save(FileReference fileReference) {
        documentoJpaRepository.save(fileReference);
    }

    @Override
    public List<FileReference> findByMatricula(Matricula matricula) {
        return documentoJpaRepository.findAllByMatricula(matricula);
    }

    @Override
    public void deleteByKey(String filePath) {
        documentoJpaRepository.deleteByKey(filePath);
    }

    @Override
    public DocumentType salvar(DocumentType documentType) {
        return documentTypeJpaRepository.save(documentType);
    }

    @Override
    public Optional<DocumentType> findById(Long idDocument) {
        return documentTypeJpaRepository.findById(idDocument);
    }

    @Override
    public List<FileReference> findAllByMatricula(Matricula matr) {
        return documentoJpaRepository.findAllByMatricula(matr);
    }
}
