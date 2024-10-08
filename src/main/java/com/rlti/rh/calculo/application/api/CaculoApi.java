package com.rlti.rh.calculo.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Calculo")
@RequestMapping("/v1/calculo")
public interface CaculoApi {

    @PostMapping("/efetuar-calculo/{mesCompetencia}")
    @ResponseStatus(code = HttpStatus.OK)
    boolean efetuarCalculo(String mesCompetencia);

    @PutMapping("/concluido/{mesCompetencia}/{numeroMatricula}/{status}")
    @ResponseStatus(code = HttpStatus.OK)
    void atualizarStatus(@PathVariable String mesCompetencia, @PathVariable String numeroMatricula, @PathVariable boolean status);

    @DeleteMapping("/{mesCompetencia}/{numeroMatricula}")
    @ResponseStatus(code = HttpStatus.OK)
    void deleteFolha(@PathVariable String mesCompetencia, @PathVariable String numeroMatricula);
}
