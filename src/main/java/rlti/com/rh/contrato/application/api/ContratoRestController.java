package rlti.com.rh.contrato.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import rlti.com.rh.contrato.application.api.response.ContratoIdResponse;
import rlti.com.rh.contrato.service.ContratoService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ContratoRestController implements ContratoApi {
    private final ContratoService contratoService;

    @Override
    public ContratoIdResponse novoContrato(ContratoRequest request) {
        return contratoService.novoContrato(request);
    }
}
