package com.rlti.rh.folha.application.api;

import com.rlti.rh.folha.domain.Vencimentos;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class VencimentosRequest {
    String codigo;
    BigDecimal valorVencimento;
    Boolean dedutivel;

    public VencimentosRequest(Vencimentos vencimentos) {
        this.codigo = vencimentos.getCodigo().toString();
        this.valorVencimento = vencimentos.getValorVencimento();
        this.dedutivel = vencimentos.getDedutivel();
    }


    public static List<VencimentosRequest> converte(List<Vencimentos>vencimentos){
        return vencimentos.stream()
                .map(VencimentosRequest::new)
                .toList();
    }
}
