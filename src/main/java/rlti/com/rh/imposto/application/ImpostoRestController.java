package rlti.com.rh.imposto.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ImpostoRestController implements ImpostoApi {


    @Override
    public boolean criarInss(InssRequest inssRequest) {
        return false;
    }
}
