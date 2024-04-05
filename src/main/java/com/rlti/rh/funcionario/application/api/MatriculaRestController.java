package com.rlti.rh.funcionario.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.funcionario.service.MatriculaService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MatriculaRestController implements MatriculaApi {

    private final MatriculaService matriculaService;

    @Override
    public String novaMatricula(String cpf) {
        return matriculaService.novaMatricula(cpf);
    }
}
