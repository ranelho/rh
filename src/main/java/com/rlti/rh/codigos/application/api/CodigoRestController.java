package com.rlti.rh.codigos.application.api;

import com.rlti.rh.codigos.service.CodigoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CodigoRestController implements CodigoApi {
    private final CodigoService codigoService;

    @Override
    public CodigoResponse newCodigo(CodigoRequest codigoRequest) {
        return codigoService.newCodigo(codigoRequest);
    }

    @Override
    public List<CodigoResponse> getAllCodigos() {
        return codigoService.getAllCodigos();    }
}
