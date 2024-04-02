package rlti.com.rh.contrato.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import rlti.com.rh.contrato.application.request.SetorRequest;
import rlti.com.rh.contrato.application.response.SetorIdReponse;
import rlti.com.rh.contrato.service.SetorService;

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
