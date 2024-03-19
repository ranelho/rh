package rlti.com.rh.funcionario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import rlti.com.rh.funcionario.application.api.FuncionarioRequest;
import rlti.com.rh.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "FUNCIONARIO")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_funcionario", nullable = false)
    private Long idFuncionario;

    private String nomeCompleto;
    @CPF
    @Column(unique = true)
    private String cpf;
    private LocalDate dataNascimento;
    @Column(unique = true)
    private Long matricula;
    @Enumerated(EnumType.STRING)
    private GrauDeInstrucao grauDeInstrucao;

    @OneToOne
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "funcionario")
    @JsonIgnore
    private List<Dependente> dependentes;

    @OneToOne
    private Contato contato;

    @OneToOne
    @JoinColumn(name = "formacao_id_formacao")
    private Formacao formacao;

    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime updatedAt;

    public Funcionario(FuncionarioRequest request) {
        this.nomeCompleto = Utils.formatName(request.nomeCompleto());
        this.cpf = request.cpf();
        this.dataNascimento = request.dataNascimento();
        this.matricula = Long.valueOf(Utils.gerarMatricula());
        this.grauDeInstrucao = request.grauDeInstrucao();
        this.sexo = request.sexo();
    }
}
