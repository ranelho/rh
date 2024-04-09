package com.rlti.rh.horas.service;

import com.rlti.rh.calculo.repository.VencimentoRepository;
import com.rlti.rh.codigos.domain.Codigo;
import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.contrato.repository.ContratoRepository;
import com.rlti.rh.folha.application.api.VencimentosRequest;
import com.rlti.rh.folha.domain.Vencimentos;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import com.rlti.rh.horas.application.api.HorasRequest;
import com.rlti.rh.horas.application.api.HorasUpdateRequest;
import com.rlti.rh.horas.domain.Horas;
import com.rlti.rh.horas.repository.CodigoRepository;
import com.rlti.rh.horas.repository.HorasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void regristrarHoras(HorasRequest horasRequest) {
        List<Vencimentos> list = new ArrayList<>();
        Matricula matricula = matriculaRepository.findByNumeroMatricula(horasRequest.numeroMatricula());
        Contrato contrato = contratoRepository.findByMatricula(matricula);

        // Método para calcular os vencimentos com base nas horas extras e noturnas
        calcularVencimentos(horasRequest, contrato, list);

        Horas horas = new Horas(horasRequest, matricula, contrato);
        horas = horasRepository.salvarHoras(horas);

        if (!list.isEmpty()) {
            horas.setVencimentos(vencimentosRepository.saveAll(list));
            horasRepository.salvarHoras(horas);
        }
    }

    private void calcularVencimentos(HorasRequest horasRequest, Contrato contrato, List<Vencimentos> vencimentos) {
        BigDecimal valorVencimento;
        VencimentosRequest vencimento = new VencimentosRequest();
        if (horasRequest.horasNoturnas() > 0 || horasRequest.horasExtras() > 0){
            valorVencimento = calcularValorHorasExtras(contrato.getCargo().getSalarioBase().getValorSalario(),
                    horasRequest.horasExtras(), horasRequest.horasNoturnas());
            vencimento.setCodigo("002");
            vencimento.setValorVencimento(valorVencimento);
            vencimento.setDedutivel(true);
            horasRequest.vencimentos().add(vencimento);
        }
        for (VencimentosRequest vencimentoRequest : horasRequest.vencimentos()) {
            Codigo codigo = codigoRepository.findByCodigo(vencimentoRequest.getCodigo());

            if (codigo != null && contrato != null) {
                if ("001".equals(codigo.getCod())) {
                    valorVencimento = contrato.getCargo().getSalarioBase().getValorSalario();
                    vencimentoRequest.setValorVencimento(valorVencimento);
                }
                vencimentos.add(new Vencimentos(vencimentoRequest, codigo));
            }
        }
    }


    @Override
    public void atualizarHoras(String numeroMatricula, Date mesReferencia, HorasUpdateRequest horasRequest) {
        Matricula matricula  = matriculaRepository.findByNumeroMatricula(numeroMatricula);
        Horas horas = horasRepository.findHorasByNumeroMatriculaAndMesReferencia(matricula, mesReferencia);
        horas.updateHoras(horasRequest);
        horasRepository.salvarHoras(horas);
    }
}
