package com.rlti.rh.folha.application.api;

import com.rlti.rh.folha.domain.FolhaMensal;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record FolhaMensalResponse(
        Long idFolhaMensal,
        Long idContrato,
        Long idFuncionario,
        String ctps,
        String pis,
        String banco,
        String agencia,
        String conta,
        LocalDate dataPagamento,
        String nomeFuncionario,
        String cpf,
        String numeroMatricula,
        LocalDate dataAdmissao,
        String cargo,
        String setor,
        String mesCompetencia,
        Integer quantidadeDependentes,
        Integer diasTrabalhados,
        BigDecimal salarioBruto,
        BigDecimal valorDescontoInss,
        Double aliquotaInss,
        BigDecimal valorDescontoIrrf,
        Double aliquotaIrrf,
        BigDecimal fgts,
        BigDecimal salarioLiquido,
        BigDecimal totalVencimentos,
        BigDecimal totalDescontos
) {
    public FolhaMensalResponse(FolhaMensal folhaMensal) {
        this(
                folhaMensal.getIdFolhaMensal(),
                folhaMensal.getIdContrato(),
                folhaMensal.getIdFuncionario(),
                folhaMensal.getCtps(),
                folhaMensal.getPis(),
                folhaMensal.getBanco(),
                folhaMensal.getAgencia(),
                folhaMensal.getConta(),
                folhaMensal.getDataPagamento(),
                folhaMensal.getNomeFuncionario(),
                folhaMensal.getCpf(),
                folhaMensal.getNumeroMatricula(),
                folhaMensal.getDataAdmissao(),
                folhaMensal.getCargo(),
                folhaMensal.getSetor(),
                folhaMensal.getMesCompetencia(),
                folhaMensal.getQuantidadeDependentes(),
                folhaMensal.getDiasTrabalhados(),
                folhaMensal.getSalarioBruto(),
                folhaMensal.getValorDescontoInss(),
                folhaMensal.getAliquotaInss(),
                folhaMensal.getValorDescontoIrrf(),
                folhaMensal.getAliquotaIrrf(),
                folhaMensal.getFgts(),
                folhaMensal.getSalarioLiquido(),
                folhaMensal.getTotalVencimentos(),
                folhaMensal.getTotalDescontos()
        );
    }
}
