package com.rlti.rh.calculo.service;

import com.rlti.rh.calculo.process.InssResult;
import com.rlti.rh.calculo.process.IrResult;
import com.rlti.rh.calculo.repository.DescontosRepository;
import com.rlti.rh.calculo.repository.VencimentosFolhaRepository;
import com.rlti.rh.config.AppConfig;
import com.rlti.rh.empresa.application.repository.EmpresaRepository;
import com.rlti.rh.empresa.domain.Empresa;
import com.rlti.rh.folha.application.api.FolhaMensaRequest;
import com.rlti.rh.folha.domain.Descontos;
import com.rlti.rh.folha.domain.FolhaMensal;
import com.rlti.rh.folha.domain.Vencimentos;
import com.rlti.rh.folha.domain.VencimentosFolha;
import com.rlti.rh.folha.infra.FolhaMensalJPARepository;
import com.rlti.rh.folha.repository.FolhaMensalRepository;
import com.rlti.rh.funcionario.repository.DependenteRepository;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import com.rlti.rh.handler.APIException;
import com.rlti.rh.horas.domain.Horas;
import com.rlti.rh.horas.repository.CodigoRepository;
import com.rlti.rh.horas.repository.HorasRepository;
import com.rlti.rh.imposto.doman.Inss;
import com.rlti.rh.imposto.doman.Irrf;
import com.rlti.rh.imposto.repository.ImpostoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.rlti.rh.calculo.process.CalculoInss.calcularINSS;
import static com.rlti.rh.calculo.process.CalculoIrrf.calcularImpostoRenda;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculoApplicationService implements CalculoService {

    private final FolhaMensalJPARepository folhaMensalJPARepository;
    private final ImpostoRepository impostoRepository;
    private final MatriculaRepository matriculaRepository;
    private final DependenteRepository dependenteRepository;
    private final HorasRepository horasRepository;
    private final FolhaMensalRepository folhaRepository;
    private final DescontosRepository descontosRepository;
    private final EmpresaRepository empresaRepository;
    private final AppConfig appConfig;
    private final CodigoRepository codigoRepository;
    private final VencimentosFolhaRepository vencimentosFolhaRepository;

    @Override
    public void atualizarStatus(String mesCompetencia, String numeroMatricula, boolean status) {
        log.info("[start] - atualizarStatus - Service");
        FolhaMensal folhaMensal = folhaRepository.findByMatriculaAndMesCompetencia(numeroMatricula, mesCompetencia)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Folha não encontrada"));
        Horas horas = horasRepository.findHorasByMesCompetenciaAndMatricula(mesCompetencia,
                matriculaRepository.findByNumeroMatricula(numeroMatricula));
        horas.setCompetenciaFechada(status);
        folhaMensal.setStatus(status);
        horasRepository.salvarHoras(horas);
        folhaRepository.saveFolhaMensal(folhaMensal);
        log.info("[end] - atualizarStatus - Service");
    }

    @Override
    public void deleteFolha(String mesCompetencia, String numeroMatricula) {
        log.info("[start] - deleteFolha - Service");
        Optional<FolhaMensal> folhaMensal = folhaRepository.findByMatriculaAndMesCompetenciaAndStatus(numeroMatricula, mesCompetencia, false);
        folhaMensal.ifPresentOrElse(
                folhaMensalJPARepository::delete,
                () -> {
                    throw APIException.build(HttpStatus.BAD_REQUEST, "Folha não encontrada");
                }
        );
        log.info("[end] - deleteFolha - Service");
    }

    @Override
    public boolean efetuarCalculo(String competencia) {
        log.info("[start] - efetuarCalculo - Service");
        List<Horas> horasTrabalhadas = horasRepository.findAllHorasTrue(competencia);
        if (!horasTrabalhadas.isEmpty()) {
            YearMonth competenciaYearMonth = YearMonth.parse(competencia);
            horasTrabalhadas.parallelStream().forEach(horas -> processarFolhaMensal(
                    competencia, horas, impostoRepository.findVigenciaInss(competenciaYearMonth),
                    impostoRepository.findVigenciaIrrf(competenciaYearMonth)));
            log.info("[end] - efetuarCalculo - Service");
            return true;
        }
        log.info("[end] - efetuarCalculo - Service");
        return false;
    }

    private void processarFolhaMensal(String competencia, Horas horas, List<Inss> inss, List<Irrf> irrf) {
        log.info("[start] - processarFolhaMensal - Service");
        Optional<FolhaMensal> optionalFolhaMensal =
                folhaRepository.findByMatriculaAndMesCompetencia(horas.getMatricula().getNumeroMatricula(), competencia);

        if (optionalFolhaMensal.isEmpty() || Boolean.FALSE.equals(optionalFolhaMensal.get().getStatus())) {
            int dependentes = dependenteRepository.countDependenteFuncionario(horas.getContrato().getMatricula().getFuncionario());
            InssResult inssResult = calcularINSS(horas.getVencimentos(), inss);
            IrResult irResult = calcularImpostoRenda(inssResult.getValorLiquido(), irrf, dependentes);
            BigDecimal auxilioTransporte = BigDecimal.ZERO;
            BigDecimal descontoAuxilioTransporte = BigDecimal.ZERO;
            if (horas.getContrato().getAuxilioTransporte() != null) {
                auxilioTransporte = BigDecimal.valueOf(horas.getContrato().getAuxilioTransporte().getValorUnitario() * horas.getContrato().getQuantidadeValeTransporte());
                descontoAuxilioTransporte = horas.getSalarioBase().multiply(BigDecimal.valueOf(0.06));
            }
            FolhaMensalData dados = FolhaMensalData.builder()
                    .horas(horas)
                    .irResult(irResult)
                    .inssResult(inssResult)
                    .totalVencimentos(inssResult.getTotalVencimentos())
                    .totalDescontos(inssResult.getInssCalculado().add(irResult.getIrrfCalculado().add(descontoAuxilioTransporte)))
                    .valorAuxilioTransporte(auxilioTransporte)
                    .build();
            if (optionalFolhaMensal.isPresent() && Boolean.FALSE.equals(optionalFolhaMensal.get().getStatus())) {
                folhaRepository.delete(optionalFolhaMensal.get());
            }
            log.info("[save] - processarFolhaMensal - Service");
            saveFolha(horas, dados, inssResult, irResult, descontoAuxilioTransporte);
        }
        log.info("[end] - processarFolhaMensal - Service");
    }

    private void saveFolha(Horas horas, FolhaMensalData dados, InssResult inssResult, IrResult irResult, BigDecimal descontoAuxilioTransporte) {
        FolhaMensal folhaMensal = saveFolhaMensal(dados);
        saveDescontos(inssResult, folhaMensal, irResult, descontoAuxilioTransporte);
        saveVencimentos(horas.getVencimentos(), folhaMensal);
        horas.setCompetenciaFechada(true);
        horasRepository.salvarHoras(horas);
    }

    private void saveVencimentos(List<Vencimentos> vencimentos, FolhaMensal folhaMensal) {
        log.info("[start] - saveVencimentos - Service");
        List<VencimentosFolha> vencimentosFolha = vencimentos.stream()
                .map(vencimento -> {
                    VencimentosFolha vencimentoFolha = new VencimentosFolha();
                    vencimentoFolha.setCodigo(vencimento.getCodigo());
                    vencimentoFolha.setValorVencimento(vencimento.getValorVencimento());
                    vencimentoFolha.setDedutivel(vencimento.getDedutivel());
                    vencimentoFolha.setFolhaMensal(folhaMensal);
                    return vencimentoFolha;
                })
                .toList();
        vencimentosFolhaRepository.saveAll(vencimentosFolha);
        log.info("[end] - saveVencimentos - Service");
    }

    private void saveDescontos(InssResult inssResult, FolhaMensal folhaMensal, IrResult irResult, BigDecimal descontoAuxilioTransporte) {
        log.info("[start] - saveDescontos - Service");
        List<Descontos> descontosASalvar = getDescontos(inssResult, folhaMensal, irResult, descontoAuxilioTransporte);
        folhaMensal.setDescontos(descontosRepository.saveAll(descontosASalvar));
        folhaRepository.saveFolhaMensal(folhaMensal);
        log.info("[end] - saveDescontos - Service");
    }

    private List<Descontos> getDescontos(InssResult inssResult, FolhaMensal folhaMensal, IrResult irResult, BigDecimal descontoAuxilioTransporte) {
        log.info("[start] - getDescontos - Service");
        Descontos descontoInss = new Descontos(inssResult, folhaMensal, codigoRepository.findByCodigo("003"));
        List<Descontos> descontosASalvar = new ArrayList<>();
        if (irResult.getIrrfCalculado().compareTo(BigDecimal.ZERO) > 0) {
            Descontos descontoIrrf = new Descontos(irResult, folhaMensal, codigoRepository.findByCodigo("004"));
            descontosASalvar.add(descontoIrrf);
        }
        if (descontoAuxilioTransporte.compareTo(BigDecimal.ZERO) > 0) {
            Descontos descontoTransporte = new Descontos(descontoAuxilioTransporte, folhaMensal, codigoRepository.findByCodigo("016"));
            descontosASalvar.add(descontoTransporte);
        }
        descontosASalvar.add(descontoInss);
        log.info("[end] - getDescontos - Service");
        return descontosASalvar;
    }

    private FolhaMensal saveFolhaMensal(FolhaMensalData dados) {
        Empresa empresa = empresaRepository.getByCnpj(appConfig.getCnpj());
        FolhaMensaRequest folhaMensaRequest = new FolhaMensaRequest(dados);
        return folhaRepository.saveFolhaMensal(new FolhaMensal(folhaMensaRequest, empresa));
    }
}
