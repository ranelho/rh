package com.rlti.rh.folha.application.api;

import com.rlti.rh.folha.domain.Vencimentos;

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
    public static List<VencimentosResponse> converte(List<Vencimentos> vencimentos) {
        return vencimentos
                .stream()
                .map(VencimentosResponse::new)
                .toList();
    }
}
