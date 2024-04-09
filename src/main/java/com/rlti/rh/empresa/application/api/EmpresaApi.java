package com.rlti.rh.empresa.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Empresa", description = "Empresa APIs")
@RequestMapping("/v1/empresas")
public interface EmpresaApi {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    EmpresaResponse saveEmpresa(@Valid @RequestBody EmpresaRequest empresaRequest);

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    List<EmpresaListResponse> getAllEmpresas();

    @GetMapping(value = "/{idEmpresa}")
    @ResponseStatus(code = HttpStatus.OK)
    EmpresaDetalhadoResponse getOneEmpresa(@PathVariable Long idEmpresa);

    @GetMapping(value = "/cnpj")
    @ResponseStatus(code = HttpStatus.OK)
    EmpresaDetalhadoResponseCnpj getByCnpj(@RequestParam String cnpj);

    @DeleteMapping(value = "/{idEmpresa}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteEmpresa(@PathVariable Long idEmpresa);

    @PatchMapping(value = "/{idEmpresa}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updateEmpresa(@PathVariable Long idEmpresa,
                       @Valid @RequestBody EmpresaUpdateRequest empresaUpdateRequest);
}