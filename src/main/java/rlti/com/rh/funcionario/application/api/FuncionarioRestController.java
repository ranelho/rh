package rlti.com.rh.funcionario.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import rlti.com.rh.funcionario.application.api.request.FuncionarioRequest;
import rlti.com.rh.funcionario.application.api.response.FuncionarioIdResponse;
import rlti.com.rh.funcionario.application.api.response.FuncionarioResponse;
import rlti.com.rh.funcionario.service.FuncionarioService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FuncionarioRestController implements FuncionarioApi {

    private final FuncionarioService funcionarioService;

    @Override
    public FuncionarioIdResponse novoFuncionario(FuncionarioRequest request) {
        return funcionarioService.novoFuncionario(request);
    }

    @Override
    public FuncionarioResponse findFuncionarioById(Long idFuncionario) {
        return funcionarioService.findFuncionarioById(idFuncionario);
    }

    @Override
    public List<FuncionarioResponse> findFuncionariosByNome(String nome) {
        return funcionarioService.findFuncionariosByNome(nome);
    }
}
