package com.rlti.rh.calculo.service;

import com.rlti.rh.calculo.InssResult;
import com.rlti.rh.calculo.IrResult;
import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.horas.domain.HorasTrabalhadas;

import java.math.BigDecimal;

public record FolhaMensalData(String competencia, HorasTrabalhadas horasTrabalhadas, Contrato contrato,
                              IrResult irResult, InssResult inssResult, BigDecimal totalVencimentos,
                              BigDecimal totalDescontos) {
}
