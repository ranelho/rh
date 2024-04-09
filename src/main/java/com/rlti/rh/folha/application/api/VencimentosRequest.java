package com.rlti.rh.folha.application.api;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class VencimentosRequest {
    String codigo;
    BigDecimal valorVencimento;
    Boolean dedutivel;
}
