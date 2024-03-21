package rlti.com.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.funcionario.application.api.request.FuncionarioRequest;
import rlti.com.rh.funcionario.application.api.response.FuncionarioIdResponse;
import rlti.com.rh.funcionario.application.api.response.FuncionarioResponse;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.repository.FuncionarioRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuncionarioAplicationService implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Override
    public FuncionarioIdResponse novoFuncionario(FuncionarioRequest request) {
        Funcionario funcionario = funcionarioRepository.save(new Funcionario(request));
        return FuncionarioIdResponse.builder().id(funcionario.getIdFuncionario()).build();
    }

    @Override
    public FuncionarioResponse findFuncionarioById(Long idFuncionario) {
        log.info("Buscando funcionario pelo id {}", idFuncionario);
        return new FuncionarioResponse(funcionarioRepository.findFuncionarioById(idFuncionario));
    }

    @Override
    public List<FuncionarioResponse> findFuncionariosByNome(String nome) {
        log.info("Buscando funcionario pelo nome {}", nome);
        return FuncionarioResponse.converte(funcionarioRepository.findFuncionariosByNome(nome));
    }
}
