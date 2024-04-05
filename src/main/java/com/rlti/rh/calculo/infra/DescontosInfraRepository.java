package com.rlti.rh.calculo.infra;

import com.rlti.rh.calculo.repository.DescontosRepository;
import com.rlti.rh.folha.domain.Descontos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DescontosInfraRepository implements DescontosRepository {
    private final DescontosJpaRepository descontosJpaRepository;

    @Override
    public List<Descontos> saveAll(List<Descontos> descontoInss) {
        return descontosJpaRepository.saveAll(descontoInss);
    }

    @Override
    public void save(Descontos descontoInss) {
        descontosJpaRepository.save(descontoInss);
    }
}
