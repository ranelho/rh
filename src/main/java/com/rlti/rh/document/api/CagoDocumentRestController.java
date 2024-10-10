package com.rlti.rh.document.api;

import com.rlti.rh.document.api.request.CagoDocumentRequest;
import com.rlti.rh.document.service.CagoDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CagoDocumentRestController implements CagoDocumentApi {

    private final CagoDocumentService cagoDocumentService;


    @Override
    public void postCagoDocument(Long idCargo, List<CagoDocumentRequest> cagoDocumentRequest) {
        cagoDocumentService.postCagoDocument(idCargo, cagoDocumentRequest);
    }
}
