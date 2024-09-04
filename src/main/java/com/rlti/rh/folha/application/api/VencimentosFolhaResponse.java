package com.rlti.rh.folha.application.api;

import com.rlti.rh.folha.domain.Vencimentos;
import com.rlti.rh.folha.domain.VencimentosFolha;

import java.util.List;

import static com.rlti.rh.utils.Utils.formatarMoeda;

public record VencimentosFolhaResponse(
        String codigo,
        String descricao,
        String valorVencimento
) {
    public VencimentosFolhaResponse (VencimentosFolha vencimentos) {
        this(
                vencimentos.getCodigo().getCod(),
                vencimentos.getCodigo().getDescricao(),
                formatarMoeda(vencimentos.getValorVencimento())
        );
    }
    public static List<VencimentosFolhaResponse> converte(List<VencimentosFolha> vencimentos) {
        return vencimentos
                .stream()
                .map(VencimentosFolhaResponse::new)
                .toList();
    }
}