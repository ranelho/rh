package com.rlti.rh.contrato.application.api;

import com.rlti.rh.contrato.application.request.AuxilioTransporteRequest;
import com.rlti.rh.contrato.application.response.AuxilioTransporteResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Auxilio Transporte")
@RequestMapping("/auxilio-transporte")
public interface AuxilioTransporteApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    void newAuxilioTransporte(@Valid @RequestBody AuxilioTransporteRequest request);

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    List<AuxilioTransporteResponse> findAllAuxilioTransportes();

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    void updateAuxilioTransporte(@PathVariable("id") Long id, @Valid @RequestBody AuxilioTransporteRequest request);
}
