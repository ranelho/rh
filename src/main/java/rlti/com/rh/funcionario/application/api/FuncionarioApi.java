package rlti.com.rh.funcionario.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api/funcionarios")
public interface FuncionarioApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    boolean novoFuncionario(FuncionarioRequest request);
}
