package com.rlti.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.rlti.rh.funcionario.application.api.request.DependenteRequest;
import com.rlti.rh.funcionario.domain.Dependente;
import com.rlti.rh.funcionario.domain.Funcionario;
import com.rlti.rh.funcionario.repository.DependenteRepository;
import com.rlti.rh.funcionario.repository.FuncionarioRepository;

@Service
@RequiredArgsConstructor
public class DependenteApplicationService implements DependenteService {

    private final DependenteRepository  dependenteRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Override
    public void newDependenteFuncionario(String cpf, DependenteRequest request) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByCpf(cpf);
        Dependente dependente = dependenteRepository.saveDependente(new Dependente(funcionario,request));
        funcionario.addDependente(dependente);
        funcionarioRepository.saveFuncionario(funcionario);
    }

    @Override
    public void deleteDependenteFuncionario(String cpfDependente) {
        Dependente dependente = dependenteRepository.findByCpf(cpfDependente);
        dependenteRepository.deleteDependenteFuncionario(dependente);
    }
}
