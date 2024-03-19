package rlti.com.rh.funcionario.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CARGO")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_cargo", nullable = false)
    private Long idCargo;

    private String nomeCargo;
    private GrauDeInstrucao grauDeInstrucao;
    private String descricaoCargo;
    private Integer quantidadeDeHorasSemanais;

    @OneToOne
    @JoinColumn(name = "salario_base_id_salario_base")
    private SalarioBase salarioBase;
}