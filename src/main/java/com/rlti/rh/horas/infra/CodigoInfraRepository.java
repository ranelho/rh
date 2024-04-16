package com.rlti.rh.horas.infra;

import com.rlti.rh.codigos.domain.Codigo;
import com.rlti.rh.horas.repository.CodigoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CodigoInfraRepository implements CodigoRepository {
    private final CodigoJpaRepository codigoJpaRepository;

    @Override
    public Codigo findByCodigo(String codigo) {
        return codigoJpaRepository.findByCod(codigo);
    }

    @Override
    public Codigo save(Codigo codigo) {
        return codigoJpaRepository.save(codigo);
    }

    @Override
    public List<Codigo> findAll() {
        return codigoJpaRepository.findAll();
    }

    @Override
    public Optional<Codigo> findByCodigoOrDescricao(String cod, String descricao) {
        return codigoJpaRepository.findByCodOrDescricao(cod, descricao);
    }

    @Override
    public List<Codigo> saveAll(List<Codigo> codigos) {
        return codigoJpaRepository.saveAll(codigos);
    }
}
