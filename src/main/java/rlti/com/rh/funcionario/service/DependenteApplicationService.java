package rlti.com.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rlti.com.rh.funcionario.application.api.request.DependenteRequest;
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
    public void novoDependente(String cpf, DependenteRequest request) {
        Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
        Dependente dependente = dependenteRepository.salvaDependente(new Dependente(funcionario,request));
        funcionario.adDependente(dependente);
        funcionarioRepository.salvaFuncionario(funcionario);
    }

    @Override
    public void removerDependente(String cpfDependente) {
        Dependente dependente = dependenteRepository.findByCpf(cpfDependente);
        dependenteRepository.removerDependente(dependente);
    }
}
