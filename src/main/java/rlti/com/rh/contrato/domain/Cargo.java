package rlti.com.rh.contrato.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rlti.com.rh.contrato.application.request.CargoRequest;
import rlti.com.rh.funcionario.domain.SalarioBase;
import rlti.com.rh.funcionario.domain.enums.GrauDeInstrucao;

import static rlti.com.rh.utils.Utils.formatText;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CARGO")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_seq_generator")
    @SequenceGenerator(name="cargo_seq_generator", sequenceName = "cargo_sequence", allocationSize=1)
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
        this.nomeCargo = formatText(request.nomeCargo());
        this.grauDeInstrucao = request.grauDeInstrucao();
        this.descricaoCargo = request.descricaoCargo();
        this.quantidadeDeHorasSemanais = request.quantidadeDeHorasSemanais();
        this.salarioBase = salarioBase;
    }
}