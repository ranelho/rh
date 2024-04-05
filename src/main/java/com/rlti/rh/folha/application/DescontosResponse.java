package com.rlti.rh.folha.application;

import com.rlti.rh.folha.domain.Descontos;

import java.math.BigDecimal;
import java.util.List;

public record DescontosResponse(
        String codigo,
        String descricao,
        BigDecimal valorDesconto
) {
    public DescontosResponse (Descontos descontos) {
        this(
                descontos.getCodigo(),
                descontos.getDescricao(),
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
