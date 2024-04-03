package rlti.com.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.Contato;
import rlti.com.rh.funcionario.repository.ContatoRepository;

@Repository
@RequiredArgsConstructor
public class ContatoInfraRepository implements ContatoRepository {
    private final ContatoJpaRepository contatoJpaRepository;

    @Override
    public Contato saveContato(Contato contato) {
        return contatoJpaRepository.save(contato);
    }
}
