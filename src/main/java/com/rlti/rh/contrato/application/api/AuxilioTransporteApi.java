package com.rlti.rh.contrato.application.api;

import com.rlti.rh.contrato.application.request.AuxilioTransporteRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Auxilio Transporte")
@RequestMapping("/auxilio-transporte")
public interface AuxilioTransporteApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    void newAuxilioTransporte(@Valid @RequestBody AuxilioTransporteRequest request);
}
