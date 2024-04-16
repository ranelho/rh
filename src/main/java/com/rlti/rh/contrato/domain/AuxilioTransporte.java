package com.rlti.rh.contrato.domain;

import com.rlti.rh.contrato.application.request.AuxilioTransporteRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public AuxilioTransporte(AuxilioTransporteRequest request) {
        this.valorUnitario = request.valorUnitario();
        this.descricao = request.descricao();
    }
}
