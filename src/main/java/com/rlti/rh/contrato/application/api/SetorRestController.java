package com.rlti.rh.contrato.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.rlti.rh.contrato.application.request.SetorRequest;
import com.rlti.rh.contrato.application.response.SetorIdReponse;
import com.rlti.rh.contrato.service.SetorService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SetorRestController implements SetorApi {
    private final SetorService setorService;

    @Override
    public SetorIdReponse newSetor(SetorRequest request) {
        return setorService.newSetor(request);
    }
}
