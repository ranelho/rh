package rlti.com.rh.imposto.application;

public record InssRequest(
        Double contribuicao,
        Double aliquota,
        String vigencia
) {}