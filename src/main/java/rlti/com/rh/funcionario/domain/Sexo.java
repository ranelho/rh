package rlti.com.rh.funcionario.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sexo {
    MASCULINO("M", "Masculino"),
    FEMININO("F", "Feminino");

    private final String codigo;
    private final String descricao;
}
