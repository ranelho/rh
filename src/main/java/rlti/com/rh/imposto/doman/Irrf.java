package rlti.com.rh.imposto.doman;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rlti.com.rh.imposto.application.IrrfRequest;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Irrf")
public class Irrf {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "irpf_seq_generator")
    @SequenceGenerator(name="irpf_seq_generator", sequenceName = "irpf_sequence", allocationSize=1)
    @Column(name = "id_irpf", nullable = false)
    private Long idIrpf;

    private Double contribuicao;
    private Double aliquota;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;
    private Double baseCalculo;

    public Irrf(IrrfRequest irrfRequest) {
        this.contribuicao = irrfRequest.contribuicao();
        this.aliquota = irrfRequest.aliquota();
        this.inicioVigencia = irrfRequest.inicioVigencia();
        this.fimVigencia = irrfRequest.fimVigencia();
        this.baseCalculo = irrfRequest.baseCalculo();
    }


}
