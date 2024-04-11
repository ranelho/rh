package com.rlti.rh.contrato.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.rh.contrato.application.request.CargoRequest;
import com.rlti.rh.funcionario.domain.SalarioBase;
import com.rlti.rh.funcionario.domain.enums.GrauDeInstrucao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.rlti.rh.utils.Utils.formatText;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CARGO")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_seq_generator")
    @SequenceGenerator(name = "cargo_seq_generator", sequenceName = "cargo_sequence", allocationSize = 1)
    @Column(name = "id_cargo", nullable = false)
    private Long idCargo;

    private String nomeCargo;
    @Enumerated(EnumType.STRING)
    private GrauDeInstrucao grauDeInstrucao;
    private Boolean exigeCursoSuperior;
    private String descricaoCargo;
    private Integer quantidadeDeHorasSemanais;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cargo")
    @JsonIgnore
    private List<SalarioBase> salarios;

    public Cargo(CargoRequest request) {
        this.nomeCargo = formatText(request.nomeCargo());
        this.grauDeInstrucao = request.grauDeInstrucao();
        this.descricaoCargo = request.descricaoCargo();
        this.quantidadeDeHorasSemanais = request.quantidadeDeHorasSemanais();
        this.exigeCursoSuperior = request.exigeCursoSuperior();
    }
}