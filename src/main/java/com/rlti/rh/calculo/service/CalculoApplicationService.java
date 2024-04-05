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

    void efetuarCalculo(String competencia){
        List<HorasTrabalhadas> horasTrabalhadas = horasRepository.findAllHoras(competencia);
        for (HorasTrabalhadas h : horasTrabalhadas) {
            Contrato contrato = contratoRepository.findByMatricula(matriculaRepository.findByNumeroMatricula(h.getMatricula().getNumeroMatricula()));

            //calculo inss e irrf
            List<Inss> inss = impostoRepository.findVigenciaInss(YearMonth.parse(competencia));
            List<Irrf> irrf = impostoRepository.findVigenciaIrrf(YearMonth.parse(competencia));
            BigDecimal salarioFuncionario = contrato.getCargo().getSalarioBase().getValorSalario();
            int dependentes = dependenteRepository.countDependenteFuncionario(contrato.getMatricula().getFuncionario());
            InssResult inssResult = CalculoInss.calcularINSS(salarioFuncionario, inss);
            IrResult irResult = CalculoIrrf.calcularImpostoRenda(inssResult.getValorLiquido(), irrf, dependentes);
            BigDecimal totalDescontos = inssResult.getInssCalculado().add(irResult.getIrrfCalculado());

            //criar calculo de horas extras
            BigDecimal extras = BigDecimal.valueOf(h.getHorasExtras()).multiply(BigDecimal.valueOf(0.25));
            BigDecimal totalVencimentos = salarioFuncionario.add(extras);

            FolhaMensal folhaMensal = saveFolhaMensal(competencia, h, contrato, salarioFuncionario, irResult, inssResult, dependentes, totalVencimentos, totalDescontos);
            saveDescontos(inssResult, folhaMensal, irResult);
        }
    }

    private FolhaMensal saveFolhaMensal(String competencia, HorasTrabalhadas h, Contrato contrato, BigDecimal salarioFuncionario,
                                        IrResult irResult, InssResult inssResult, int dependentes, BigDecimal totalVencimentos, BigDecimal totalDescontos) {
        FolhaMensaRequest folhaMensaRequest = new FolhaMensaRequest(contrato, salarioFuncionario, irResult, inssResult,
                                contrato.getMatricula().getFuncionario(), competencia, dependentes, h, totalVencimentos, totalDescontos);
        return folhaRepository.saveFolhaMensal(new FolhaMensal(folhaMensaRequest));
    }

    private void saveDescontos(InssResult inssResult, FolhaMensal folhaMensal, IrResult irResult) {
        Descontos descontoInss = new Descontos(inssResult, folhaMensal);
        List<Descontos> descontosASalvar = new ArrayList<>();

        if (irResult.getIrrfCalculado() != null) {
            Descontos descontoIrrf = new Descontos(irResult, folhaMensal);
            descontosASalvar.add(descontoIrrf);
        }
        descontosASalvar.add(descontoInss);
        descontosRepository.saveAll(descontosASalvar);
    }
}
