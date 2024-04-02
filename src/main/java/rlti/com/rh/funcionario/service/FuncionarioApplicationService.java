package rlti.com.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.funcionario.application.request.FuncionarioRequest;
import rlti.com.rh.funcionario.application.request.FuncionarioUpdateRequest;
import rlti.com.rh.funcionario.application.response.FuncionarioIdResponse;
import rlti.com.rh.funcionario.application.response.FuncionarioResponse;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.repository.FuncionarioRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuncionarioApplicationService implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Override
    public FuncionarioIdResponse newFuncionario(FuncionarioRequest request) {
        Funcionario funcionario = funcionarioRepository.saveFuncionario(new Funcionario(request));
        return FuncionarioIdResponse.builder().id(funcionario.getIdFuncionario()).build();
    }

    @Override
    public FuncionarioResponse findFuncionarioById(Long idFuncionario) {
        log.info("Buscando funcionario pelo id {}", idFuncionario);
        return new FuncionarioResponse(funcionarioRepository.findFuncionarioById(idFuncionario));
    }

    @Override
    public List<FuncionarioResponse> findAllFuncionariosByNome(String nome) {
        log.info("Buscando funcionario pelo nome {}", nome);
        return FuncionarioResponse.converte(funcionarioRepository.findAllFuncionariosByNome(nome));
    }

    @Override
    public void updateFuncionario(Long id, FuncionarioUpdateRequest request) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioById(id);
        funcionario.update(request);
        funcionarioRepository.saveFuncionario(funcionario);
    }

    @Override
    public FuncionarioResponse findFuncionarioByMatricula(String matricula) {
        return new FuncionarioResponse(funcionarioRepository.findFuncionarioByMatricula(matricula));
    }

    @Override
    public FuncionarioResponse findFuncionarioByCpf(String cpf) {
        return new FuncionarioResponse(funcionarioRepository.findFuncionarioByCpf(cpf));
    }
}
