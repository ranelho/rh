package com.rlti.rh.horas.service;

import com.rlti.rh.calculo.repository.VencimentoRepository;
import com.rlti.rh.codigos.domain.Codigo;
import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.contrato.repository.ContratoRepository;
import com.rlti.rh.folha.application.api.VencimentosRequest;
import com.rlti.rh.folha.domain.Vencimentos;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.funcionario.domain.SalarioBase;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import com.rlti.rh.handler.APIException;
import com.rlti.rh.horas.application.api.HorasRequest;
import com.rlti.rh.horas.domain.Horas;
import com.rlti.rh.horas.repository.CodigoRepository;
import com.rlti.rh.horas.repository.HorasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.rlti.rh.calculo.process.CalculadoraHoraExtra.calcularValorHorasExtras;

@Service
@RequiredArgsConstructor
public class HorasApplicationService implements HorasService {

    private final HorasRepository horasRepository;
    private final MatriculaRepository matriculaRepository;
    private final CodigoRepository codigoRepository;
    private final VencimentoRepository vencimentosRepository;
    private final ContratoRepository contratoRepository;

    @Override
    public void regristrarHoras(String numeroMatricula, String mesCompetencia, HorasRequest horasRequest) {
        List<Vencimentos> list = new ArrayList<>();
        Matricula matricula = matriculaRepository.findByNumeroMatricula(numeroMatricula);
        Contrato contrato = contratoRepository.findByMatricula(matricula);
        BigDecimal salarioBase = calcularVencimentos(horasRequest, contrato, list, mesCompetencia);
        Horas horas = new Horas(horasRequest, matricula, contrato, salarioBase, mesCompetencia);
        horas = horasRepository.salvarHoras(horas);
        if (!list.isEmpty()) {
            List<Vencimentos> vencimentos = vencimentosRepository.saveAll(list);
            horas.setVencimentos(vencimentos);
            horasRepository.salvarHoras(horas);
            for (Vencimentos vencimento : vencimentos) {
                vencimento.setHoras(horas);
                vencimentosRepository.save(vencimento);
            }
        }
    }

    @Override
    public void addVencimentos(String numeroMatricula, String mesReferencia, List<VencimentosRequest> listRequest) {
        Matricula matricula = matriculaRepository.findByNumeroMatricula(numeroMatricula);
        Horas horas = horasRepository.findHorasByNumeroMatriculaAndMesReferencia(matricula, mesReferencia);
        List<Vencimentos> vencimentos = new ArrayList<>();
        addVencimentosToList(listRequest, vencimentos);
        for (Vencimentos vencimento : vencimentos) {
            vencimento.setHoras(horas);
            vencimentosRepository.save(vencimento);
        }
        horas.setVencimentos(vencimentos);
        horasRepository.salvarHoras(horas);
    }

    @Override
    public void deleHoras(String numeroMatricula, String mesReferencia) {
        Horas horas = horasRepository.findHorasByNumeroMatriculaAndMesReferencia2(matriculaRepository.findByNumeroMatricula(numeroMatricula), mesReferencia)
                .orElseThrow(()-> APIException.build(HttpStatus.BAD_REQUEST, "Horas não localizado"));
            vencimentosRepository.deleteVencimentosByHoras(horas);
            horasRepository.deletarHoras(horas);

    }

    private BigDecimal calcularVencimentos(HorasRequest horasRequest, Contrato contrato, List<Vencimentos> vencimentos, String mesCompetencia) {
        Optional<Horas> horasOptional = horasRepository.findHorasByNumeroMatriculaAndMesReferencia2(contrato.getMatricula(), mesCompetencia);
        horasOptional.ifPresent(vencimentosRepository::deleteVencimentosByHoras);
        horasOptional.ifPresent(horasRepository::deletarHoras);

        SalarioBase salarioFuncionario = contrato.getCargo().getSalarios().stream()
                .filter(salario -> Objects.equals(salario.getNivel(), contrato.getNivel()))
                .findFirst()
                .orElse(null);
        validaNivel(salarioFuncionario);
        if (horasRequest.getHorasNoturnas() > 0 || horasRequest.getHorasExtras() > 0)
            montarECalcularHorasExtras(horasRequest, salarioFuncionario);

        montarVencimentos(horasRequest, salarioFuncionario.getValorSalario(), "001");
        addVencimentosToList(horasRequest.getVencimentos(), vencimentos);
        return salarioFuncionario.getValorSalario();
    }

    private static void montarECalcularHorasExtras(HorasRequest horasRequest, SalarioBase salarioFuncionario) {
        BigDecimal valorVencimento = calcularValorHorasExtras(salarioFuncionario.getValorSalario(),
                horasRequest.getHorasExtras(), horasRequest.getHorasNoturnas());
        montarVencimentos(horasRequest, valorVencimento, "002");
    }

    private void addVencimentosToList(List<VencimentosRequest> horasRequest, List<Vencimentos> vencimentos) {
        for (VencimentosRequest vencimentoRequest : horasRequest) {
            Codigo codigo = codigoRepository.findByCodigo(vencimentoRequest.getCodigo());
            if (codigo != null) {
                vencimentos.add(new Vencimentos(vencimentoRequest, codigo));
            }
        }
    }

    private static void montarVencimentos(HorasRequest horasRequest, BigDecimal valorVencimento, String codigo) {
        VencimentosRequest vencimento = new VencimentosRequest();
        vencimento.setCodigo(codigo);
        vencimento.setValorVencimento(valorVencimento);
        vencimento.setDedutivel(true);
        horasRequest.getVencimentos().add(vencimento);
    }

    private static void validaNivel(SalarioBase salarioFuncionario) {
        if (salarioFuncionario == null) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Salário do funcionário não encontrado para o nível do contrato.");
        }
    }


}