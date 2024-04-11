package com.rlti.rh.funcionario.service;

import com.rlti.rh.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.rlti.rh.funcionario.domain.Funcionario;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.funcionario.repository.FuncionarioRepository;
import com.rlti.rh.funcionario.repository.MatriculaRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatriculaApplicationService implements MatriculaService {
    private final FuncionarioRepository funcionarioRepository;
    private final MatriculaRepository matriculaRepository;
    private final Utils utils;

    @Override
    public String novaMatricula(String cpf) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByCpf(cpf);
        Matricula matricula = matriculaRepository.novaMatricula(new Matricula(funcionario, utils.gerarMatricula()));
        funcionario.addMatricula(matricula);
        funcionarioRepository.saveFuncionario(funcionario);
        return matricula.getNumeroMatricula();
    }
}
