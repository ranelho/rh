package com.rlti.rh.funcionario.application.api;

import com.rlti.rh.funcionario.application.api.request.ContaPagamentoRequest;
import com.rlti.rh.funcionario.application.api.request.FormacaoRequest;
import com.rlti.rh.funcionario.application.api.request.FuncionarioRequest;
import com.rlti.rh.funcionario.application.api.request.FuncionarioUpdateRequest;
import com.rlti.rh.funcionario.application.api.response.DocumentsFuncionarioResponse;
import com.rlti.rh.funcionario.application.api.response.FuncionarioComFormacaoResponse;
import com.rlti.rh.funcionario.application.api.response.FuncionarioIdResponse;
import com.rlti.rh.funcionario.application.api.response.FuncionarioResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Funcionário", description = "API de Funcionário")
@RequestMapping("/v1/funcionarios")
public interface FuncionarioApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    FuncionarioIdResponse newFuncionario(@Valid @RequestBody FuncionarioRequest request);

    @GetMapping("/by-name/{nome}")
    @ResponseStatus(code = HttpStatus.OK)
    Page<FuncionarioResponse> findAllFuncionariosByNome(@PathVariable("nome") String nome, Pageable pageable);

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    Page<FuncionarioResponse> findAllFuncionarios(Pageable pageable);

    @PatchMapping("/update/{cpf}")
    @ResponseStatus(code = HttpStatus.OK)
    void updateFuncionario(@PathVariable("cpf") String cpf,@Valid @RequestBody FuncionarioUpdateRequest request);

    @GetMapping("/matricula/{matricula}")
    @ResponseStatus(code = HttpStatus.OK)
    FuncionarioResponse findFuncionarioByMatricula(@PathVariable("matricula") String matricula);

    @GetMapping("/cpf/{cpf}")
    @ResponseStatus(code = HttpStatus.OK)
    FuncionarioResponse findFuncionarioByCpf(@PathVariable("cpf") String cpf);

    @PostMapping("add-conta-pagamento/{cpf}")
    @ResponseStatus(code = HttpStatus.CREATED)
    void newContaPagamento(@PathVariable("cpf") String cpf, @Valid @RequestBody ContaPagamentoRequest contaPagamentoRequest);

    @PutMapping("update/conta-pagamento/{cpf}")
    @ResponseStatus(code = HttpStatus.OK)
    void updateContaPagamento(@PathVariable("cpf") String cpf, @Valid @RequestBody ContaPagamentoRequest contaPagamentoRequest);

    @PostMapping("add-formacao/{cpf}")
    @ResponseStatus(code = HttpStatus.CREATED)
    void addFormacao(@PathVariable("cpf") String cpf, @Valid @RequestBody FormacaoRequest formacaoRequest);

    @GetMapping("/all-com-formacao/")
    @ResponseStatus(code = HttpStatus.OK)
    List<FuncionarioComFormacaoResponse> findAllFuncionariosComFormacao();

    @GetMapping("/documents/{matricula}")
    @ResponseStatus(code = HttpStatus.OK)
    DocumentsFuncionarioResponse findDocumentsByFuncionario(@PathVariable("matricula") String matricula);
}
