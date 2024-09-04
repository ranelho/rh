package com.rlti.rh.folha.application.api;

import com.rlti.rh.codigos.domain.Codigo;

public record CodigoResponse(
        String cod,
        String descricao
) {
    public CodigoResponse(Codigo codigo) {
        this(codigo.getCod(), codigo.getDescricao());
    }
}
