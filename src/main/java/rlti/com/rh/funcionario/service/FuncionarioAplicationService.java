package rlti.com.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.funcionario.application.api.FuncionarioRequest;
import rlti.com.rh.funcionario.application.api.FuncionarioService;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.repository.FuncionarioRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuncionarioAplicationService implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Override
    public boolean novoFuncionario(FuncionarioRequest request) {
       return funcionarioRepository.save(new Funcionario(request));
    }
}
