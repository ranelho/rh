package com.rlti.rh.imposto.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Tag(name = "Imposto", description = "API de Imposto")
@RequestMapping("/v1/imposto")
public interface ImpostoApi {

    @PostMapping("/inss")
    @ResponseStatus(CREATED)
    @Operation(summary = "Cria um novo inss", description = "Cria um novo inss", tags = "Imposto")
    boolean criarInss(@Valid @RequestBody InssRequest inssRequest);

    @PostMapping("/irrf")
    @ResponseStatus(CREATED)
    @Operation(summary = "Cria um novo irrf", description = "Cria um novo irrf", tags = "Imposto")
    boolean criarIrrf(@Valid @RequestBody IrrfRequest irrfRequest);

    @GetMapping("/inss/{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Consulta um inss", description = "Consulta um inss", tags = "Imposto")
    InssResponse consultarInss(@PathVariable Long id);

    @GetMapping("/irrf/{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Consulta um irrf", description = "Consulta um irrf", tags = "Imposto")
    IrrfResponse consultarIrrf(@PathVariable Long id);

    @GetMapping("/inss/{inicioVigencia}/{fimVigencia}")
    @ResponseStatus(OK)
    @Operation(summary = "Consulta todos os inss", description = "Consulta todos os inss", tags = "Imposto")
    List<InssResponse> consultarAllInss(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicioVigencia,
                                        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fimVigencia);

}
