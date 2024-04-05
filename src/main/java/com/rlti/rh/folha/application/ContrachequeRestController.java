package com.rlti.rh.folha.application;

import com.rlti.rh.folha.service.ContrachequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContrachequeRestController implements ContrachequeApi {
    private final ContrachequeService contrachequeService;

    @Override
    public ContrachequeResponse getContracheque(ContrachequeRequest request) {
        return contrachequeService.getContracheque(request);
    }
}
