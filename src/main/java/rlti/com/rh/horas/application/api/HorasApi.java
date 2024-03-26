package rlti.com.rh.horas.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/v1/horas")
public interface HorasApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void registrarHoras(@Valid @RequestBody HorasRequest horasRequest);

    @PutMapping("/{numeroMatricula}/{mesReferencia}")
    @ResponseStatus(HttpStatus.OK)
    void atualizarHoras(@PathVariable String numeroMatricula, @PathVariable Date mesReferencia,
                        @Valid @RequestBody HorasUpdateRequest horasUpdateRequest);
}
