package com.rlti.rh.empresa.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Empresa", description = "Api Empresa")
@RequestMapping("/v1/empresas")
public interface EmpresaApi {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    EmpresaIdResponse saveEmpresa(@Valid @RequestBody EmpresaRequest empresaRequest);

    @GetMapping(value = "/cnpj")
    @ResponseStatus(code = HttpStatus.OK)
    EmpresaResponse getByCnpj(@RequestParam String cnpj);

    @PatchMapping(value = "/{idEmpresa}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updateEmpresa(@PathVariable Long idEmpresa, @Valid @RequestBody EmpresaUpdateRequest empresaUpdateRequest);
}