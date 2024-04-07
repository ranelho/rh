package com.rlti.rh.funcionario.application.api;

import com.rlti.rh.funcionario.application.request.ContaPagamentoRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.rlti.rh.funcionario.application.request.FuncionarioRequest;
import com.rlti.rh.funcionario.application.request.FuncionarioUpdateRequest;
import com.rlti.rh.funcionario.application.response.FuncionarioIdResponse;
import com.rlti.rh.funcionario.application.response.FuncionarioResponse;

import java.util.List;

@Tag(name = "Funcionário", description = "API de Funcionário")
@RequestMapping("/v1/funcionarios")
public interface FuncionarioApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    FuncionarioIdResponse newFuncionario(@Valid @RequestBody FuncionarioRequest request);

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    FuncionarioResponse findFuncionarioById(@PathVariable("id") Long id);

    @GetMapping("/nome/{nome}")
    @ResponseStatus(code = HttpStatus.OK)
    List<FuncionarioResponse> findAllFuncionariosByNome(@PathVariable("nome") String nome);

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    void updateFuncionario(@PathVariable("id") Long id,@Valid @RequestBody FuncionarioUpdateRequest request);

    @GetMapping("/matricula/{matricula}")
    @ResponseStatus(code = HttpStatus.OK)
    FuncionarioResponse findFuncionarioByMatricula(@PathVariable("matricula") String matricula);

    @GetMapping("/cpf/{cpf}")
    @ResponseStatus(code = HttpStatus.OK)
    FuncionarioResponse findFuncionarioByCpf(@PathVariable("cpf") String cpf);

    @PostMapping("conta-pagamento/{cpf}")
    @ResponseStatus(code = HttpStatus.CREATED)
    void newContaPagamento(@PathVariable("cpf") String cpf, @Valid @RequestBody ContaPagamentoRequest contaPagamentoRequest);
}
