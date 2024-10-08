package com.rlti.rh.funcionario.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.rlti.rh.funcionario.application.request.DependenteRequest;

@Tag(name = "Dependentes" , description = "API de Dependentes")
@RequestMapping("/v1/funcionarios/dependentes")
public interface DependenteApi {

    @PostMapping("/{cpfFuncionario}")
    @ResponseStatus(HttpStatus.CREATED)
    void newDependenteFuncionario(@PathVariable("cpfFuncionario") @Valid @CPF(message = "CPF inválido") String cpfFuncionario,
                                  @Valid @RequestBody DependenteRequest request);

    @DeleteMapping("/remover/{cpfDependente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteDependenteFuncionario(@PathVariable("cpfDependente") String cpfDependente);

}
