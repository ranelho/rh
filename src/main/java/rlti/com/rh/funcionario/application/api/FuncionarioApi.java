package rlti.com.rh.funcionario.application.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rlti.com.rh.funcionario.application.api.request.FuncionarioRequest;
import rlti.com.rh.funcionario.application.api.response.FuncionarioIdResponse;
import rlti.com.rh.funcionario.application.api.response.FuncionarioResponse;

import java.util.List;

@Tag(name = "Funcionário", description = "API de Funcionário")
@RequestMapping("/v1/funcionarios")
public interface FuncionarioApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    FuncionarioIdResponse novoFuncionario(@Valid @RequestBody FuncionarioRequest request);

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Consulta um funcionario", description = "Consulta um funcionario", tags = "Funcionario")
    FuncionarioResponse findFuncionarioById(@PathVariable("id") Long id);

    @GetMapping("/nome/{nome}")
    @ResponseStatus(code = HttpStatus.OK)
    List<FuncionarioResponse> findFuncionariosByNome(@PathVariable("nome") String nome);

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    void updateFuncionario(@PathVariable("id") Long id,@Valid @RequestBody FuncionarioRequest request);

    @PatchMapping("/{id}/cargo/{idContrato}")
    @ResponseStatus(code = HttpStatus.OK)
    void addFuncionarioContrato(@PathVariable("matricula") String matricula, @PathVariable("idContrato") Long idContrato);

    @GetMapping("/matricula/{matricula}")
    @ResponseStatus(code = HttpStatus.OK)
    FuncionarioResponse findFuncionarioByMatricula(@PathVariable("matricula") String matricula);

    @GetMapping("/cpf/{cpf}")
    @ResponseStatus(code = HttpStatus.OK)
    FuncionarioResponse findFuncionarioByCpf(@PathVariable("cpf") String cpf);

}
