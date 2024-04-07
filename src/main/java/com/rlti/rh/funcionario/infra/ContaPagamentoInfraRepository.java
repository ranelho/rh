package com.rlti.rh.funcionario.infra;

import com.rlti.rh.funcionario.domain.ContaPagamento;
import com.rlti.rh.funcionario.repository.ContaPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContaPagamentoInfraRepository implements ContaPagamentoRepository {
    private final ContaPagamentoJpaRepository contaPagamentoJpaRepository;

    @Override
    public ContaPagamento saveContaPagamento(ContaPagamento contaPagamento) {
        return contaPagamentoJpaRepository.save(contaPagamento);
    }
}
