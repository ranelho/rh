package rlti.com.rh.funcionario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import rlti.com.rh.funcionario.application.api.request.DependenteRequest;
import rlti.com.rh.funcionario.domain.enums.GrauParentesco;

import java.time.LocalDate;

import static rlti.com.rh.utils.Utils.formatName;

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
        this.nomeCompleto = formatName(request.nomeCompleto());
        this.cpf = request.cpf();
        this.dataNascimento = request.dataNascimento();
        this.grauParentesco = request.grauParentesco();
    }
}
