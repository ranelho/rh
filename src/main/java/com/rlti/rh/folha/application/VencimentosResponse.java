package com.rlti.rh.folha.application;

import com.rlti.rh.folha.domain.Descontos;
import com.rlti.rh.folha.domain.Vencimentos;

import java.math.BigDecimal;
import java.util.List;

public record VencimentosResponse(
        String codigo,
        String descricao,
        BigDecimal valorDesconto
) {
    public VencimentosResponse (Vencimentos vencimentos) {
        this(
                vencimentos.getCodigo(),
                vencimentos.getDescricao(),
                vencimentos.getValorVencimento()
        );
    }
    public static List<VencimentosResponse> converte(List<Vencimentos> descontos) {
        return descontos
                .stream()
                .map(VencimentosResponse::new)
                .toList();
    }
}
