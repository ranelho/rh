package rlti.com.rh.funcionario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rlti.com.rh.funcionario.application.request.ContatoRequest;
import rlti.com.rh.funcionario.domain.Contato;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.repository.ContatoRepository;
import rlti.com.rh.funcionario.repository.FuncionarioRepository;

@Service
@RequiredArgsConstructor
public class ContatoApplicationService implements ContatoService {

    private final ContatoRepository contatoRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Override
    public void addContatoFuncionario(String cpf, ContatoRequest contatoRequest) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByCpf(cpf);
        if (funcionario.getContato() == null) {
            funcionario.addContato(contatoRepository.saveContato(new Contato(contatoRequest)));
        }else {
            updateContato(cpf,contatoRequest);
        }
    }

    @Override
    public void updateContato(String cpf, ContatoRequest contatoRequest) {
        Contato contato = funcionarioRepository.findFuncionarioByCpf(cpf).getContato();
        contato.update(contatoRequest);
        contatoRepository.saveContato(contato);
    }
}