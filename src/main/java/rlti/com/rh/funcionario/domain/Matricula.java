package rlti.com.rh.funcionario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import rlti.com.rh.utils.Utils;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "MATRICULA")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_matricula", nullable = false)
    private Long idMatricula;

    @Column(unique = true)
    private String numeroMatricula;
    @CreatedDate
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "funcionario_id_funcionario")
    @JsonIgnore
    private Funcionario funcionario;

    public Matricula(Funcionario funcionario) {
        this.numeroMatricula = Utils.gerarMatricula();
        this.funcionario = funcionario;
    }
}
