package com.rlti.rh.document.api;

import com.rlti.rh.document.api.request.DocumentoTypeRequest;
import com.rlti.rh.document.api.response.DocumentoTypeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/document-type")
public interface DocumentTypeApi {

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<DocumentoTypeResponse> allDocumentsType();

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    DocumentoTypeResponse createDocumentType(DocumentoTypeRequest request);
}
