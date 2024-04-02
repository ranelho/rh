package rlti.com.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rlti.com.rh.funcionario.application.request.DependenteRequest;
import rlti.com.rh.funcionario.domain.Dependente;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.repository.DependenteRepository;
import rlti.com.rh.funcionario.repository.FuncionarioRepository;

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
