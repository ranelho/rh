package rlti.com.rh.funcionario.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rlti.com.rh.funcionario.application.api.request.ContatoRequest;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CONTATO")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato_seq_generator")
    @SequenceGenerator(name="contato_seq_generator", sequenceName = "contato_sequence", allocationSize=1)
    @Column(name = "id_contato", nullable = false)
    private Long idContato;

    @Email
    private String email;
    private String telefone;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String numero;
    private String complemento;
    private String pais;

    public Contato(ContatoRequest contatoRequest) {
        this.email = contatoRequest.email();
        this.telefone = contatoRequest.telefone();
        this.rua = contatoRequest.rua();
        this.bairro = contatoRequest.bairro();
        this.cidade = contatoRequest.cidade();
        this.estado = contatoRequest.estado();
        this.cep = contatoRequest.cep();
        this.numero = contatoRequest.numero();
        this.complemento = contatoRequest.complemento();
        this.pais = contatoRequest.pais();
    }

    public void update(ContatoRequest contatoRequest) {
        this.email = contatoRequest.email();
        this.telefone = contatoRequest.telefone();
        this.rua = contatoRequest.rua();
        this.bairro = contatoRequest.bairro();
        this.cidade = contatoRequest.cidade();
        this.estado = contatoRequest.estado();
        this.cep = contatoRequest.cep();
        this.numero = contatoRequest.numero();
        this.complemento = contatoRequest.complemento();
        this.pais = contatoRequest.pais();
    }
}
