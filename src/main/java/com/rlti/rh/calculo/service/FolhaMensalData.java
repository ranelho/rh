package com.rlti.rh.calculo.service;

import com.rlti.rh.calculo.process.InssResult;
import com.rlti.rh.calculo.process.IrResult;
import com.rlti.rh.horas.domain.Horas;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record FolhaMensalData(
        Horas horas,
        IrResult irResult,
        InssResult inssResult,
        BigDecimal totalVencimentos,
        BigDecimal totalDescontos,
        BigDecimal valorAuxilioTransporte
)
{}
