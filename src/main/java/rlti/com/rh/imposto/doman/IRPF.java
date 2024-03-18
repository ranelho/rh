package rlti.com.rh.imposto.doman;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "IRPF")
public class IRPF {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_irpf", nullable = false)
    private Long idIrpf;

    private Double contribuicao;
    private Double aliquota;
    private String vigencia;
    private Double deducao;
}
