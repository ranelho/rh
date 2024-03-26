package rlti.com.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.contrato.repository.ContratoRepository;
import rlti.com.rh.funcionario.application.api.request.FuncionarioRequest;
import rlti.com.rh.funcionario.application.api.response.FuncionarioIdResponse;
import rlti.com.rh.funcionario.application.api.response.FuncionarioResponse;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.contrato.repository.CargoRepository;
import rlti.com.rh.funcionario.repository.FuncionarioRepository;
import rlti.com.rh.funcionario.repository.MatriculaRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuncionarioApplicationService implements FuncionarioService {

    private final MatriculaRepository matriculaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final ContratoRepository contratoRepository;

    @Override
    public FuncionarioIdResponse novoFuncionario(FuncionarioRequest request) {
        Funcionario funcionario = funcionarioRepository.salvaFuncionario(new Funcionario(request));
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

    @Override
    public void updateFuncionario(Long id, FuncionarioRequest request) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioById(id);
        funcionario.update(request);
        funcionarioRepository.salvaFuncionario(funcionario);
    }

    @Override
    public void addFuncionarioContrato(String matricula, Long idContrato) {
        contratoRepository.findContratoById(idContrato);
    }

    @Override
    public FuncionarioResponse findFuncionarioByMatricula(String matricula) {
        return new FuncionarioResponse(funcionarioRepository.findFuncionarioByMatricula(matricula));
    }

    @Override
    public FuncionarioResponse findFuncionarioByCpf(String cpf) {
        return new FuncionarioResponse(funcionarioRepository.findByCpf(cpf));
    }
}
