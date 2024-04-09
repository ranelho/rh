package com.rlti.rh.horas.infra;

import com.rlti.rh.codigos.domain.Codigo;
import com.rlti.rh.horas.repository.CodigoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CodigoInfraRepository implements CodigoRepository {
    private final CodigoJpaRepository codigoJpaRepository;

    @Override
    public Codigo findByCodigo(String codigo) {
        return codigoJpaRepository.findByCod(codigo);
    }
}
