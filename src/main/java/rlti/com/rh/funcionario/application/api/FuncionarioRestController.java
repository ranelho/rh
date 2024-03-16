package rlti.com.rh.funcionario.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FuncionarioRestController implements FuncionarioApi {

    private final FuncionarioService funcionarioService;

    @Override
    public boolean novoFuncionario(FuncionarioRequest request) {
        return funcionarioService.novoFuncionario(request);
    }
}
