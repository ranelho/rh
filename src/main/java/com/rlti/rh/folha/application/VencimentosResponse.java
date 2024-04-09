package com.rlti.rh.folha.application;

import com.rlti.rh.folha.domain.Vencimentos;

import java.math.BigDecimal;
import java.util.List;

public record VencimentosResponse(
        CodigoResponse codigo,
        BigDecimal valorVencimento
) {
    public VencimentosResponse (Vencimentos vencimentos) {
        this(
                new CodigoResponse(vencimentos.getCodigo()),
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
