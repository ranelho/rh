package com.rlti.rh.calculo.process;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class InssResult {
    private String codigo;
    private String descricao;
    private BigDecimal salarioBruto;
    private double aliquota;
    private BigDecimal inssCalculado;
    private BigDecimal valorLiquido;
}
