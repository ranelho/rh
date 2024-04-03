package rlti.com.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.domain.Matricula;
import rlti.com.rh.funcionario.repository.FuncionarioRepository;
import rlti.com.rh.funcionario.repository.MatriculaRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatriculaApplicationService implements MatriculaService {
    private final FuncionarioRepository funcionarioRepository;
    private final MatriculaRepository matriculaRepository;

    @Override
    public String novaMatricula(String cpf) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByCpf(cpf);
        Matricula matricula = matriculaRepository.novaMatricula(new Matricula(funcionario));
        funcionario.addMatricula(matricula);
        funcionarioRepository.saveFuncionario(funcionario);
        return matricula.getNumeroMatricula();
    }
}
