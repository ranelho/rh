package rlti.com.rh.funcionario.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import rlti.com.rh.funcionario.application.api.request.FuncionarioRequest;
import rlti.com.rh.funcionario.application.api.response.FuncionarioIdResponse;

@Tag(name = "Funcionário", description = "API de Funcionário")
@RequestMapping("/v1/funcionario")
public interface FuncionarioApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Funcionario criado com sucesso")
    FuncionarioIdResponse novoFuncionario(@Valid @RequestBody FuncionarioRequest request);

}
