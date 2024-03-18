package rlti.com.rh.imposto.application;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api/v1/imposto")
public interface ImpostoApi {

    @PostMapping(value = "/inss", produces = "application/json", consumes = "application/json"  )
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Inss criado com sucesso")
    boolean criarInss (@Valid InssRequest inssRequest);
}
