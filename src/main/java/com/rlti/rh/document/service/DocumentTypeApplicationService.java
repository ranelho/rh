package com.rlti.rh.document.service;

import com.rlti.rh.document.api.request.DocumentoTypeRequest;
import com.rlti.rh.document.api.response.DocumentoTypeResponse;
import com.rlti.rh.document.domain.DocumentType;
import com.rlti.rh.document.repository.DocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DocumentTypeApplicationService implements DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentoTypeResponse> allDocumentsType() {
        List<DocumentType> documentType = documentTypeRepository.findAll();
        return DocumentoTypeResponse.convert(documentType);
    }

    @Override
    public DocumentoTypeResponse createDocumentType(DocumentoTypeRequest request) {
        DocumentType documentType = documentTypeRepository.save(new DocumentType(request));
        return DocumentoTypeResponse.builder()
                .id(documentType.getId())
                .descricao(documentType.getDescricao())
                .build();
    }
}
