package com.rlti.rh.document.api;

import com.rlti.rh.document.api.request.DocumentoTypeRequest;
import com.rlti.rh.document.api.response.DocumentoTypeResponse;
import com.rlti.rh.document.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DocumentTypeRestController implements DocumentTypeApi {

    private final DocumentTypeService documentTypeService;

    @Override
    public List<DocumentoTypeResponse> allDocumentsType() {
        return documentTypeService.allDocumentsType();
    }

    @Override
    public DocumentoTypeResponse createDocumentType(DocumentoTypeRequest request) {
        return documentTypeService.createDocumentType(request);
    }
}
