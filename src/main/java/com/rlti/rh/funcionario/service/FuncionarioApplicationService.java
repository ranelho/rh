package com.rlti.rh.funcionario.service;

import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.contrato.repository.ContratoRepository;
import com.rlti.rh.document.domain.FileReference;
import com.rlti.rh.document.repository.DocumentoRepository;
import com.rlti.rh.funcionario.application.api.request.ContaPagamentoRequest;
import com.rlti.rh.funcionario.application.api.request.FormacaoRequest;
import com.rlti.rh.funcionario.application.api.request.FuncionarioRequest;
import com.rlti.rh.funcionario.application.api.request.FuncionarioUpdateRequest;
import com.rlti.rh.funcionario.application.api.response.DocumentsFuncionarioResponse;
import com.rlti.rh.funcionario.application.api.response.FuncionarioComFormacaoResponse;
import com.rlti.rh.funcionario.application.api.response.FuncionarioIdResponse;
import com.rlti.rh.funcionario.application.api.response.FuncionarioResponse;
import com.rlti.rh.funcionario.domain.ContaPagamento;
import com.rlti.rh.funcionario.domain.Formacao;
import com.rlti.rh.funcionario.domain.Funcionario;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.funcionario.repository.ContaPagamentoRepository;
import com.rlti.rh.funcionario.repository.FormacaoRepository;
import com.rlti.rh.funcionario.repository.FuncionarioRepository;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import com.rlti.rh.utils.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.rlti.rh.utils.AniversarioChecker.verificarAniversario;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuncionarioApplicationService implements FuncionarioService {
    private final FormacaoRepository formacaoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final EmailService emailService;
    private final ContaPagamentoRepository contaPagamentoRepository;
    private final MatriculaRepository matriculaRepository;
    private final ContratoRepository contratoRepository;
    private final DocumentoRepository documentoRepository;

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
    public Page<FuncionarioResponse> findAllFuncionariosByNome(String nome, Pageable pageable) {
        log.info("Buscando funcionario pelo nome {}", nome);
        return FuncionarioResponse.convertePageable(funcionarioRepository.findAllFuncionariosByNome(nome, pageable));
    }

    @Override
    public void updateFuncionario(String cpf, FuncionarioUpdateRequest request) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByCpf(cpf);
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
            } else {
                log.warn("Funcionario não possui email cadastrado: {}", funcionario.getNomeCompleto());
            }
        }
    }

    @Override
    public Page<Funcionario> findAllFuncionarios(Pageable pageable) {
        return funcionarioRepository.findAllFuncionarios(pageable);
    }

    @Override
    public void newContaPagamento(String cpf, ContaPagamentoRequest contaPagamentoRequest) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByCpf(cpf);
        ContaPagamento contaPagamento = contaPagamentoRepository.saveContaPagamento(new ContaPagamento(contaPagamentoRequest));
        funcionario.setContaPagamento(contaPagamento);
        funcionarioRepository.saveFuncionario(funcionario);
    }

    @Override
    public void updateContaPagamento(String cpf, ContaPagamentoRequest contaPagamentoRequest) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByCpf(cpf);
        ContaPagamento contaPagamento = funcionario.getContaPagamento();
        contaPagamento.update(contaPagamentoRequest);
        contaPagamentoRepository.saveContaPagamento(contaPagamento);
        funcionario.setContaPagamento(contaPagamento);
        funcionarioRepository.saveFuncionario(funcionario);
    }

    @Override
    public void addFormacao(String cpf, FormacaoRequest formacaoRequest) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByCpf(cpf);
        Formacao formacao = formacaoRepository.newFormacao(new Formacao(formacaoRequest, funcionario));
        funcionario.addFormacao(formacao);
        funcionarioRepository.saveFuncionario(funcionario);
    }

    @Override
    public List<FuncionarioComFormacaoResponse> findAllFuncionariosComFormacao() {
        List<Funcionario> funcionarios = funcionarioRepository.findAllFuncionariosComFormacao();
        return FuncionarioComFormacaoResponse.converte(funcionarios);
    }

    @Override
    public DocumentsFuncionarioResponse findDocumentsByFuncionario(String matricula) {
        Matricula matr = matriculaRepository.findByNumeroMatricula(matricula);
        Contrato contrato = contratoRepository.findByMatricula(matr);
        List<FileReference> fileReferences = documentoRepository.findAllByMatricula(matr);
        return new DocumentsFuncionarioResponse(matr, contrato, fileReferences);
    }
}
