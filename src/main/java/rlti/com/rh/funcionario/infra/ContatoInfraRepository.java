package rlti.com.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.Contato;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.repository.ContatoRepository;
import rlti.com.rh.handler.APIException;

@Repository
@RequiredArgsConstructor
public class ContatoInfraRepository implements ContatoRepository {
    private final ContatoJpaRepository contatoJpaRepository;

    @Override
    public Contato saveContato(Contato contato) {
        return contatoJpaRepository.save(contato);
    }
}
