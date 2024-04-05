package com.rlti.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.rlti.rh.funcionario.application.request.FuncionarioRequest;
import com.rlti.rh.funcionario.application.request.FuncionarioUpdateRequest;
import com.rlti.rh.funcionario.application.response.FuncionarioIdResponse;
import com.rlti.rh.funcionario.application.response.FuncionarioResponse;
import com.rlti.rh.funcionario.domain.Funcionario;
import com.rlti.rh.funcionario.repository.FuncionarioRepository;
import com.rlti.rh.utils.email.EmailService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.rlti.rh.utils.AniversarioChecker.verificarAniversario;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuncionarioApplicationService implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final EmailService emailService;

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

    public List<Funcionario> encontrarAniversariantes() {
        return funcionarioRepository.findAllByAniversario(LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
    }

    @Override
    public void verificaAniversarioBath() {
        List<Funcionario> funcionarios = encontrarAniversariantes();
        for (Funcionario funcionario : funcionarios) {
            Map<String, String> detalhesFuncionario = verificarAniversario(funcionario);
            if (!detalhesFuncionario.isEmpty() && detalhesFuncionario.get("email") != null) {
                emailService.enviarEmail(detalhesFuncionario.get("nomeCompleto"), detalhesFuncionario.get("email"),
                        detalhesFuncionario.get("mensagem"));
            }else {
                log.warn("Funcionario não possui email cadastrado: {}", funcionario.getNomeCompleto());
            }
        }
    }

    @Override
    public List<Funcionario> findAllFuncionarios() {
        return funcionarioRepository.findAllFuncionarios();
    }
}
