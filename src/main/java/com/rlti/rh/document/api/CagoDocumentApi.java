package com.rlti.rh.document.api;

import com.rlti.rh.document.api.request.CagoDocumentRequest;
import com.rlti.rh.document.api.response.CargoDocumentResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cago-document")
public interface CagoDocumentApi {

    @PostMapping("/{idCargo}")
    void  postCagoDocument(@PathVariable Long idCargo, @RequestBody List<CagoDocumentRequest> cagoDocumentRequest);

}
