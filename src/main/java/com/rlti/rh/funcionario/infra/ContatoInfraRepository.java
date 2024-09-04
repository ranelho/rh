package com.rlti.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.rlti.rh.funcionario.domain.Contato;
import com.rlti.rh.funcionario.repository.ContatoRepository;

@Repository
@RequiredArgsConstructor
public class ContatoInfraRepository implements ContatoRepository {
    private final ContatoJpaRepository contatoJpaRepository;

    @Override
    public Contato saveContato(Contato contato) {
        return contatoJpaRepository.save(contato);
    }
}
