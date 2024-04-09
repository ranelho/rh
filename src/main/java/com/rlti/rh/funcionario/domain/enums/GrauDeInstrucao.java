package com.rlti.rh.funcionario.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GrauDeInstrucao {
    ENSINO_FUNDAMENTAL("Ensino Fundamental"),
    ENSINO_MEDIO("Ensino Médio"),
    ENSINO_TECNICO("Ensino Técnico"),
    GRADUACAO("Graduação"),
    POS_GRADUACAO("Pós-Graduação");

    private final String descricao;
}
