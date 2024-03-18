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
@Entity(name = "INSS")
public class INSS {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_inss", nullable = false)
    private Long idInss;

    private Double contribuicao;
    private Double aliquota;
    private String vigencia;
}
