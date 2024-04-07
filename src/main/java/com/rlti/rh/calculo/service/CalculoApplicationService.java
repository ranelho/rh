package com.rlti.rh.calculo.service;

import com.rlti.rh.calculo.CalculoInss;
import com.rlti.rh.calculo.CalculoIrrf;
import com.rlti.rh.calculo.InssResult;
import com.rlti.rh.calculo.IrResult;
import com.rlti.rh.calculo.application.api.request.SimulacaoInssRequest;
import com.rlti.rh.calculo.application.api.response.SimulacaoResponse;
import com.rlti.rh.calculo.repository.DescontosRepository;
import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.contrato.repository.ContratoRepository;
import com.rlti.rh.empresa.application.repository.EmpresaRepository;
import com.rlti.rh.empresa.domain.Empresa;
import com.rlti.rh.folha.application.api.FolhaMensaRequest;
import com.rlti.rh.folha.domain.Descontos;
import com.rlti.rh.folha.domain.FolhaMensal;
import com.rlti.rh.folha.repository.FolhaMensalRepository;
import com.rlti.rh.funcionario.repository.DependenteRepository;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import com.rlti.rh.horas.domain.HorasTrabalhadas;
import com.rlti.rh.horas.repository.HorasRepository;
import com.rlti.rh.imposto.doman.Inss;
import com.rlti.rh.imposto.doman.Irrf;
import com.rlti.rh.imposto.repository.ImpostoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculoApplicationService implements CalculoService {

    private final ContratoRepository contratoRepository;
    private final ImpostoRepository impostoRepository;
    private final MatriculaRepository matriculaRepository;
    private final DependenteRepository dependenteRepository;
    private final HorasRepository horasRepository;
    private final FolhaMensalRepository folhaRepository;
    private final DescontosRepository descontosRepository;
    private final EmpresaRepository empresaRepository;


    @Override
    public SimulacaoResponse simularCalculoInss(SimulacaoInssRequest request) {
        Contrato contrato = contratoRepository.findByMatricula(matriculaRepository.findByNumeroMatricula(request.matricula()));
        List<Inss> inss = impostoRepository.findVigenciaInss(request.mesAno());
        List<Irrf> irrf = impostoRepository.findVigenciaIrrf(request.mesAno());
        int dependentes = dependenteRepository.countDependenteFuncionario(contrato.getMatricula().getFuncionario());
        BigDecimal salarioFuncionario = contrato.getCargo().getSalarioBase().getValorSalario();
        InssResult inssResult = CalculoInss.calcularINSS(salarioFuncionario, inss);
        IrResult irResult = CalculoIrrf.calcularImpostoRenda(inssResult.getValorLiquido(), irrf, dependentes);
        return new SimulacaoResponse(contrato, inssResult, irResult,request.mesAno(), dependentes);
    }

    public boolean efetuarCalculo(String competencia){
        List<HorasTrabalhadas> horasTrabalhadas = horasRepository.findAllHorasTrue(competencia);

        List<Inss> inss = impostoRepository.findVigenciaInss(YearMonth.parse(competencia));
        List<Irrf> irrf = impostoRepository.findVigenciaIrrf(YearMonth.parse(competencia));

        for (HorasTrabalhadas horas : horasTrabalhadas) {
            Optional<FolhaMensal> optionalFolhaMensal = folhaRepository.findByMatriculaAndMesCompetencia(
                    horas.getMatricula().getNumeroMatricula(), competencia);
            Contrato contrato = contratoRepository.findByMatricula(
                    matriculaRepository.findByNumeroMatricula(horas.getMatricula().getNumeroMatricula()));
            BigDecimal salarioFuncionario = contrato.getCargo().getSalarioBase().getValorSalario();
            int dependentes = dependenteRepository.countDependenteFuncionario(contrato.getMatricula().getFuncionario());
            InssResult inssResult = CalculoInss.calcularINSS(salarioFuncionario, inss);
            IrResult irResult = CalculoIrrf.calcularImpostoRenda(inssResult.getValorLiquido(), irrf, dependentes);

            BigDecimal extras = BigDecimal.valueOf(horas.getHorasExtras()).multiply(BigDecimal.valueOf(0.25));
            BigDecimal totalVencimentos = salarioFuncionario.add(extras);

            FolhaMensalData dados = new FolhaMensalData(competencia, horas, contrato, irResult, inssResult, totalVencimentos,
                    inssResult.getInssCalculado().add(irResult.getIrrfCalculado()));

           if (!optionalFolhaMensal.get().getFechada()) {
                folhaRepository.delete(optionalFolhaMensal.get());
            }
            FolhaMensal folhaMensal = saveFolhaMensal(dados);
            saveDescontos(inssResult, folhaMensal, irResult);
        }
        return true;
    }

    private FolhaMensal saveFolhaMensal(FolhaMensalData dados) {
        Empresa empresa = empresaRepository.getByCnpj("81436017000198");
        FolhaMensaRequest folhaMensaRequest = new FolhaMensaRequest(dados);
        return folhaRepository.saveFolhaMensal(new FolhaMensal(folhaMensaRequest,empresa));
    }

    private void saveDescontos(InssResult inssResult, FolhaMensal folhaMensal, IrResult irResult) {
        List<Descontos> descontosASalvar = getDescontos(inssResult, folhaMensal, irResult);
        folhaMensal.addDescontos(descontosRepository.saveAll(descontosASalvar));
        folhaRepository.saveFolhaMensal(folhaMensal);
    }

    private static List<Descontos> getDescontos(InssResult inssResult, FolhaMensal folhaMensal, IrResult irResult) {
        Descontos descontoInss = new Descontos(inssResult, folhaMensal);
        List<Descontos> descontosASalvar = new ArrayList<>();
        if (irResult.getIrrfCalculado().compareTo(BigDecimal.ZERO) > 0) {
            Descontos descontoIrrf = new Descontos(irResult, folhaMensal);
            descontosASalvar.add(descontoIrrf);
        }
        descontosASalvar.add(descontoInss);
        return descontosASalvar;
    }
}
