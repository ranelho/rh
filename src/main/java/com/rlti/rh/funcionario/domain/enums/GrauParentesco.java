package com.rlti.rh.funcionario.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GrauParentesco {
    PAI(1, "Pai"),
    MAE(2, "Mãe"),
    FILHO(3, "Filho"),
    FILHA(4, "Filha");

    private final int codigo;
    private final String descricao;
}