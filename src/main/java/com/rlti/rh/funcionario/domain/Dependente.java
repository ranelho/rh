package com.rlti.rh.funcionario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import com.rlti.rh.funcionario.application.request.DependenteRequest;
import com.rlti.rh.funcionario.domain.enums.GrauParentesco;

import java.time.LocalDate;

import static com.rlti.rh.utils.Utils.formatText;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "DEPENDENTE")
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dependente_seq_generator")
    @SequenceGenerator(name="dependente_seq_generator", sequenceName = "dependente_sequence", allocationSize=1)
    @Column(name = "id_dependente", nullable = false)
    private Long idDependente;

    private String nomeCompleto;
    @CPF
    private String cpf;
    private LocalDate dataNascimento;
    @Enumerated(EnumType.STRING)
    private GrauParentesco grauParentesco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    @JsonIgnore
    private Funcionario funcionario;

    public Dependente(Funcionario funcionario, DependenteRequest request) {
        this.funcionario = funcionario;
        this.nomeCompleto = formatText(request.nomeCompleto());
        this.cpf = request.cpf();
        this.dataNascimento = request.dataNascimento();
        this.grauParentesco = request.grauParentesco();
    }
}
