package com.rlti.rh.contrato.application.response;

import com.rlti.rh.contrato.domain.Setor;

import java.util.List;

public record SetorResponse(
        Long idSetor,
        String nomeSetor
) {
    public SetorResponse (Setor setor) {
        this(setor.getIdSetor(), setor.getNomeSetor());
    }

    public static List<SetorResponse> convert(List<Setor> setores) {
        return setores.stream().map(SetorResponse::new).toList();
    }
}
