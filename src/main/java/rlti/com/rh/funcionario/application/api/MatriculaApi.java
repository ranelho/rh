package rlti.com.rh.funcionario.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Matricula", description = "API de Matricula")
@RequestMapping("/matriculas")
public interface MatriculaApi {

    @PostMapping("cpf/{cpf}")
    @ResponseStatus(code = HttpStatus.CREATED)
    String novaMatricula(@PathVariable("cpf") String cpf);
}
