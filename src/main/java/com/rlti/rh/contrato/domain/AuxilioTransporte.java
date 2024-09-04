package com.rlti.rh.contrato.domain;

import com.rlti.rh.contrato.application.request.AuxilioTransporteRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "AUXILIO_TRANSPORTE")
public class AuxilioTransporte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auxilio_transporte_seq_generator")
    @SequenceGenerator(name="auxilio_transporte_seq_generator", sequenceName = "auxilio_transporte_sequence", allocationSize=1)
    @Column(name = "idAuxilioTransporte", nullable = false)
    private Long idAuxilioTransporte;
    private Double valorUnitario;
    private String descricao;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;

    public AuxilioTransporte(AuxilioTransporteRequest request) {
        this.valorUnitario = request.valorUnitario();
        this.descricao = request.descricao();
        this.inicioVigencia = request.inicioVigencia();
        this.fimVigencia = request.fimVigencia();
    }

    public void update(AuxilioTransporteRequest request) {
        this.valorUnitario = request.valorUnitario() == null ? this.valorUnitario : request.valorUnitario();
        this.descricao = request.descricao() == null ? this.descricao : request.descricao();
        this.inicioVigencia = request.inicioVigencia();
        this.fimVigencia = request.fimVigencia();
    }
}
