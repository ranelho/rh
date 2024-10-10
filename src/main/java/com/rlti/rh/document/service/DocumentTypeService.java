package com.rlti.rh.document.service;

import com.rlti.rh.document.api.request.DocumentoTypeRequest;
import com.rlti.rh.document.api.response.DocumentoTypeResponse;

import java.util.List;

public interface DocumentTypeService {
    List<DocumentoTypeResponse> allDocumentsType();

    DocumentoTypeResponse createDocumentType(DocumentoTypeRequest request);
}
