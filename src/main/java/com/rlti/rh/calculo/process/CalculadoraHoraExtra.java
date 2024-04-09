package com.rlti.rh.calculo.process;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CalculadoraHoraExtra {

    // Supondo um multiplicador fixo para horas extras (exemplo: 1.5 para 50% a mais)
    private static final BigDecimal MULTIPLICADOR_HORA_EXTRA = new BigDecimal("1.5");
    // Supondo um adicional para horas noturnas (exemplo: 1.2 para 20% a mais)
    private static final BigDecimal ADICIONAL_HORA_NOTURNA = new BigDecimal("1.2");

    public static BigDecimal calcularValorHorasExtras(BigDecimal salario, double horasExtras, double horasNoturnas) {
        // Calcula o valor da hora normal dividindo o salário pela quantidade de horas regulares
        BigDecimal valorHoraNormal = salario.divide(new BigDecimal("160"), 2, RoundingMode.HALF_UP); // 160 horas é uma média mensal de trabalho

        // Calcula o valor da hora extra multiplicando o valor da hora normal pelo multiplicador
        BigDecimal valorHoraExtra = valorHoraNormal.multiply(MULTIPLICADOR_HORA_EXTRA);

        // Calcula o valor total das horas extras
        BigDecimal valorTotalHorasExtras = valorHoraExtra.multiply(BigDecimal.valueOf(horasExtras));

        // Calcula o valor da hora noturna multiplicando o valor da hora normal pelo adicional
        BigDecimal valorHoraNoturna = valorHoraNormal.multiply(ADICIONAL_HORA_NOTURNA);

        // Calcula o valor total das horas noturnas
        BigDecimal valorTotalHorasNoturnas = valorHoraNoturna.multiply(BigDecimal.valueOf(horasNoturnas));

        // Retorna a soma dos valores das horas extras e noturnas
        return valorTotalHorasExtras.add(valorTotalHorasNoturnas);
    }
}
