package rlti.com.rh.funcionario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "DEPENDENTE")
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_dependente", nullable = false)
    private Long idDependente;

    private String nomeCompleto;
    private LocalDate dataNascimento;
    private GrauParentesco grauParentesco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    @JsonIgnore
    private Funcionario funcionario;

}
