package com.rlti.rh.folha.application.api;

import com.rlti.rh.empresa.application.api.EmpresaResponse;
import com.rlti.rh.folha.domain.FolhaMensal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ContrachequeResponse(
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
        BigDecimal totalVencimentos,
        BigDecimal totalDescontos,
        BigDecimal salarioLiquido,
        List<VencimentosFolhaResponse> vencimentos,
        List<DescontosResponse> descontos,
        EmpresaResponse empresa
) {
    public ContrachequeResponse(FolhaMensal folhaMensal) {
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
                folhaMensal.getTotalVencimentos(),
                folhaMensal.getTotalDescontos(),
                folhaMensal.getSalarioLiquido(),
                folhaMensal.getVencimentos().stream().map(VencimentosFolhaResponse::new).toList(),
                folhaMensal.getDescontos().stream().map(DescontosResponse::new).toList(),
                new EmpresaResponse(folhaMensal.getEmpresa())
        );
    }
}
