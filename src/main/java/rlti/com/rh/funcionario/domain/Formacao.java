package rlti.com.rh.funcionario.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rlti.com.rh.funcionario.domain.enums.SituacaoCurso;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "FORMACAO")
public class Formacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_formacao", nullable = false)
    private Long idFormacao;

    private String curso;
    private String instituicao;
    private LocalDate dataConclusao;
    @Enumerated(EnumType.STRING)
    private SituacaoCurso situacaoCurso;
}
