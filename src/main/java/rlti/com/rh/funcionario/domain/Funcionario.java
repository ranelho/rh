package rlti.com.rh.funcionario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import rlti.com.rh.funcionario.application.api.request.FuncionarioRequest;
import rlti.com.rh.funcionario.domain.enums.EstadoCivil;
import rlti.com.rh.funcionario.domain.enums.GrauDeInstrucao;
import rlti.com.rh.funcionario.domain.enums.Sexo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static rlti.com.rh.utils.Utils.formatText;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "FUNCIONARIO")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_seq_generator")
    @SequenceGenerator(name="funcionario_seq_generator", sequenceName = "funcionario_sequence", allocationSize=1)
    @Column(name = "id_funcionario", nullable = false)
    private Long idFuncionario;

    @NotNull
    private String nomeCompleto;
    @CPF
    @Column(unique = true)
    private String cpf;
    private LocalDate dataNascimento;
    private String rg;
    private LocalDate dataEmissaoRg;
    private String ctps;
    private String pis;
    private String nomePai;
    private String nomeMae;
    @Enumerated(EnumType.STRING)     private GrauDeInstrucao grauDeInstrucao;
    @Enumerated(EnumType.STRING)     private Sexo sexo;
    @Enumerated(EnumType.STRING)     private EstadoCivil estadoCivil;

    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime updatedAt;

    @OneToOne
    @JsonIgnore
    private Contato contato;

    @OneToOne
    @JsonIgnore
    private ContaPagamento contaPagamento;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "funcionario")
    @JsonIgnore
    private List<Matricula> matriculas;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "funcionario")
    @JsonIgnore
    private List<Dependente> dependentes;

    @OneToMany
    @JsonIgnore
    @JoinColumn(name = "formacao_id_formacao")
    private List<Formacao> formacao;

    public Funcionario(FuncionarioRequest request) {
        this.nomeCompleto = formatText(request.nomeCompleto());
        this.cpf = request.cpf();
        this.dataNascimento = request.dataNascimento();
        this.rg = request.rg();
        this.dataEmissaoRg = request.dataEmissaoRg();
        this.ctps = request.ctps();
        this.pis = request.pis();
        this.grauDeInstrucao = request.grauDeInstrucao();
        this.sexo = request.sexo();
        this.estadoCivil = request.estadoCivil();
        this.nomePai = request.nomePai();
        this.nomeMae = request.nomeMae();
        this.contato = new Contato(request.contatoRequest());
    }

    public void update(FuncionarioRequest request) {
        this.nomeCompleto = formatText(request.nomeCompleto());
        this.cpf = request.cpf();
        this.dataNascimento = request.dataNascimento();
        this.grauDeInstrucao = request.grauDeInstrucao();
        this.sexo = request.sexo();
    }

    public void addMatricula(Matricula matricula) {
        this.matriculas.add(matricula);
    }

    public void adDependente(Dependente dependente) {
        this.dependentes.add(dependente);
    }

    public void addContato(Contato contato) {
        this.contato = contato;
    }
}
