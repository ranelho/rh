package com.rlti.rh.folha.application;

import com.rlti.rh.folha.domain.Vencimentos;
import com.rlti.rh.utils.Utils;

import java.math.BigDecimal;
import java.util.List;

import static com.rlti.rh.utils.Utils.*;

public record VencimentosResponse(
        String codigo,
        String descricao,
        String valorVencimento
) {
    public VencimentosResponse (Vencimentos vencimentos) {
        this(
                vencimentos.getCodigo().getCod(),
                vencimentos.getCodigo().getDescricao(),
                formatarMoeda(vencimentos.getValorVencimento())
        );
    }
    public static List<VencimentosResponse> converte(List<Vencimentos> descontos) {
        return descontos
                .stream()
                .map(VencimentosResponse::new)
                .toList();
    }
}
