package com.rlti.rh.folha.application;

import com.rlti.rh.folha.domain.Descontos;

import java.math.BigDecimal;
import java.util.List;

public record DescontosResponse(
        CodigoResponse codigo,
        BigDecimal valorDesconto
) {
    public DescontosResponse (Descontos descontos) {
        this(
                new CodigoResponse(descontos.getCodigo()),
                descontos.getValorDesconto()
        );
    }
    public static List<DescontosResponse> converte(List<Descontos> descontos) {
        return descontos
                .stream()
                .map(DescontosResponse::new)
                .toList();
    }
}
