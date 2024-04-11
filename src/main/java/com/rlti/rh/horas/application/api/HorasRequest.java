package com.rlti.rh.horas.application.api;

import com.rlti.rh.folha.application.api.VencimentosRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HorasRequest {
    Integer diasTrabalhados;
    Integer faltas;
    Integer faltasJustificadas;
    Double horasExtras;
    Double horasNoturnas;
    Boolean competenciaFechada;
    List<VencimentosRequest> vencimentos;
}
