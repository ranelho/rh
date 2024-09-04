package com.rlti.rh.horas.application.api;

import com.rlti.rh.folha.application.api.VencimentosRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Horas")
@RequestMapping("/v1/horas")
public interface HorasApi {

    @PostMapping("/{numeroMatricula}/{mesReferencia}")
    @ResponseStatus(HttpStatus.CREATED)
    void registrarHoras(@PathVariable(name = "numeroMatricula") String numeroMatricula, @PathVariable(name = "mesReferencia" ) String mesReferencia,
                        @Valid @RequestBody HorasRequest horasRequest);

    @DeleteMapping("/{numeroMatricula}/{mesReferencia}")
    @ResponseStatus(HttpStatus.OK)
    void deletarHoras(@PathVariable String numeroMatricula, @PathVariable String mesReferencia);

    @PutMapping("/add-vencimentos/{numeroMatricula}/{mesReferencia}")
    @ResponseStatus(HttpStatus.OK)
    void addVencimentos(@PathVariable String numeroMatricula, @PathVariable String mesReferencia, @RequestBody List<VencimentosRequest> vencimentos);
}