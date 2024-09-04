package com.rlti.rh.calculo.process;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class InssResult {
    private BigDecimal totalVencimentos;
    private double aliquota;
    private BigDecimal inssCalculado;
    private BigDecimal valorLiquido;
}
