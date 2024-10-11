package com.rlti.rh.funcionario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.rh.funcionario.application.api.request.FormacaoRequest;
import com.rlti.rh.funcionario.domain.enums.SituacaoCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

import static com.rlti.rh.utils.Utils.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "FORMACAO")
public class Formacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formacao_seq_generator")
    @SequenceGenerator(name="formacao_seq_generator", sequenceName = "formacao_sequence", allocationSize=1)
    @Column(name = "id_formacao", nullable = false)
    private Long idFormacao;

    private String curso;
    private String instituicao;
    private Year anoInicio;
    private Year anoTermino;
    @Enumerated(EnumType.STRING)
    private SituacaoCurso situacaoCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id_funcionario")
    @JsonIgnore
    private Funcionario funcionario;

    public Formacao(FormacaoRequest formacaoRequest, Funcionario funcionario) {
        this.curso = formatText(formacaoRequest.curso());
        this.instituicao = formatText(formacaoRequest.instituicao());
        this.anoInicio = formacaoRequest.anoInicio();
        this.anoTermino = formacaoRequest.anoTermino();
        this.situacaoCurso = formacaoRequest.situacaoCurso();
        this.funcionario = funcionario;
    }
}
