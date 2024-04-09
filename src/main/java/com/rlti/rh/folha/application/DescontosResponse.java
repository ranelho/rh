package com.rlti.rh.folha.application;

import com.rlti.rh.folha.domain.Descontos;
import com.rlti.rh.utils.Utils;

import java.math.BigDecimal;
import java.util.List;

import static com.rlti.rh.utils.Utils.*;

public record DescontosResponse(
        String codigo,
        String descricao,
        String valorDesconto
) {
    public DescontosResponse (Descontos descontos) {
        this(
                descontos.getCodigo().getCod(),
                descontos.getCodigo().getDescricao(),
                formatarMoeda(descontos.getValorDesconto())
        );
    }
    public static List<DescontosResponse> converte(List<Descontos> descontos) {
        return descontos
                .stream()
                .map(DescontosResponse::new)
                .toList();
    }
}
