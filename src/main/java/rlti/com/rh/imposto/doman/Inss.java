package rlti.com.rh.imposto.doman;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rlti.com.rh.imposto.application.InssRequest;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "INSS")
public class Inss {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inss_seq_generator")
    @SequenceGenerator(name="inss_seq_generator", sequenceName = "inss_sequence", allocationSize=1)
    @Column(name = "id_inss", nullable = false)
    private Long idInss;

    private Double valorMinimo;
    private Double valorMaximo;
    private Double aliquota;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;
    private Double deducao;

    public Inss(InssRequest inssRequest) {
        this.valorMinimo = inssRequest.valorMinimo();
        this.valorMaximo = inssRequest.valorMaximo();
        this.aliquota = inssRequest.aliquota();
        this.inicioVigencia = inssRequest.inicioVigencia();
        this.fimVigencia = inssRequest.fimVigencia();
        this.deducao = inssRequest.deducao();
    }
}
