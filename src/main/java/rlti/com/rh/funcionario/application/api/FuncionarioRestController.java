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

    @Override
    public void updateFuncionario(Long id, FuncionarioRequest request) {
        funcionarioService.updateFuncionario(id, request);
    }

    @Override
    public void addFuncionarioContrato(String matricula, Long idContrato) {
        funcionarioService.addFuncionarioContrato(matricula, idContrato);
    }

    @Override
    public FuncionarioResponse findFuncionarioByMatricula(String matricula) {
        return funcionarioService.findFuncionarioByMatricula(matricula);
    }

    @Override
    public FuncionarioResponse findFuncionarioByCpf(String cpf) {
        return funcionarioService.findFuncionarioByCpf(cpf);
    }
}
