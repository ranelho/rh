package com.rlti.rh.horas.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Tag(name = "Horas")
@RequestMapping("/v1/horas")
public interface HorasApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void registrarHoras(@Valid @RequestBody HorasRequest horasRequest);

    @PutMapping("/{numeroMatricula}/{mesReferencia}")
    @ResponseStatus(HttpStatus.OK)
    void atualizarHoras(@PathVariable String numeroMatricula, @PathVariable Date mesReferencia,
                        @Valid @RequestBody HorasUpdateRequest horasUpdateRequest);
}
