package rlti.com.rh.funcionario.domain;

import jakarta.persistence.*;
import lombok.*;
import rlti.com.rh.funcionario.application.api.request.CargoRequest;

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
    @Enumerated(EnumType.STRING)
    private GrauDeInstrucao grauDeInstrucao;
    private String descricaoCargo;
    private Integer quantidadeDeHorasSemanais;

    @OneToOne
    @JoinColumn(name = "salario_base_id_salario_base")
    private SalarioBase salarioBase;

    public Cargo(CargoRequest request, SalarioBase salarioBase) {
        this.nomeCargo = request.nomeCargo();
        this.grauDeInstrucao = request.grauDeInstrucao();
        this.descricaoCargo = request.descricaoCargo();
        this.quantidadeDeHorasSemanais = request.quantidadeDeHorasSemanais();
        this.salarioBase = salarioBase;
    }
}