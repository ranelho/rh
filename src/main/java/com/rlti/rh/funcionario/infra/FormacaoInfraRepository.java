package com.rlti.rh.funcionario.infra;

import com.rlti.rh.funcionario.domain.Formacao;
import com.rlti.rh.funcionario.repository.FormacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FormacaoInfraRepository implements FormacaoRepository {
    private final FormacaoJpaRepository formacaoJpaRepository;

    @Override
    public Formacao newFormacao(Formacao formacao) {
        return  formacaoJpaRepository.save(formacao);
    }
}
