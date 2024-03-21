package rlti.com.rh.funcionario.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoCivil {
    SOLTEIRO(1, "Solteiro"),
    CASADO(2, "Casado"),
    DIVORCIADO(3, "Divorciado"),
    VIUVO(4, "Viúvo"),
    UNIAO_ESTAVEL(5, "União Estável");

    private final int codigo;
    private final String descricao;
}

