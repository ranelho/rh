package com.rlti.rh.document.service;

import com.rlti.rh.document.api.request.CagoDocumentRequest;

import java.util.List;

public interface CagoDocumentService {
    void postCagoDocument(Long idCargo, List<CagoDocumentRequest> cagoDocumentRequest);
}
