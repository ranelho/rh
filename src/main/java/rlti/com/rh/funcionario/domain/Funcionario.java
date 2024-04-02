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
import rlti.com.rh.contrato.domain.Contrato;
import rlti.com.rh.funcionario.application.api.request.FuncionarioRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static rlti.com.rh.utils.Utils.*;

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

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime updatedAt;

    @OneToOne
    private Contato contato;

    @OneToMany
    private List<Contrato> contrato;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "funcionario")
    @JsonIgnore
    private List<Dependente> dependentes;

    @OneToMany
    @JoinColumn(name = "formacao_id_formacao")
    private List<Formacao> formacao;

    public Funcionario(FuncionarioRequest request) {
        this.nomeCompleto = formatName(request.nomeCompleto());
        this.cpf = request.cpf();
        this.dataNascimento = request.dataNascimento();
        this.matricula = Long.valueOf(gerarMatricula());
        this.grauDeInstrucao = request.grauDeInstrucao();
        this.sexo = request.sexo();
    }
}
