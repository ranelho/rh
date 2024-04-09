package com.rlti.rh.empresa.domain;

import com.rlti.rh.empresa.application.api.EmpresaRequest;
import com.rlti.rh.empresa.application.api.EmpresaUpdateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dependente_seq_generator")
    @SequenceGenerator(name="empresa_seq_generator", sequenceName = "empresa_sequence", allocationSize=1)
    Long idEmpresa;
    @NotBlank
    String razaoSocial;
    @NotBlank
    private String nomeFantasia;
    @NotBlank
    @Column(unique = true)
    @CNPJ
    private String cnpj;
    @NotBlank
    private String nomeAdministrador;
    @NotBlank
    private String inscricaoMunicipal;
    @NotBlank
    private String areaAtuacao;
    @NotNull
    private LocalDate dataAbertura;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    private String telefone;
    @NotNull
    private String enderecoComercial;
    @NotNull
    private Boolean aceitaTermos;

    public Empresa(EmpresaRequest empresaRequest) {
        this.razaoSocial = empresaRequest.razaoSocial().toUpperCase();
        this.nomeFantasia = empresaRequest.nomeFantasia().toUpperCase();
        this.cnpj = empresaRequest.cnpj();
        this.nomeAdministrador = empresaRequest.nomeAdministrador().toUpperCase();
        this.inscricaoMunicipal = empresaRequest.inscricaoMunicipal();
        this.areaAtuacao = empresaRequest.areaAtuacao().toUpperCase();
        this.dataAbertura = empresaRequest.dataAbertura();
        this.email = empresaRequest.email().toLowerCase();
        this.telefone = empresaRequest.telefone();
        this.enderecoComercial = empresaRequest.enderecoComercial();
        this.aceitaTermos = empresaRequest.aceitaTermos();
    }

    public void altera(EmpresaUpdateRequest empresaUpdateRequest) {
        this.nomeFantasia = empresaUpdateRequest.getNomeFantasia().toUpperCase();
        this.nomeAdministrador = empresaUpdateRequest.getNomeAdministrador().toUpperCase();
        this.areaAtuacao = empresaUpdateRequest.getAreaAtuacao().toUpperCase();
        this.dataAbertura = empresaUpdateRequest.getDataAbertura();
        this.email = empresaUpdateRequest.getEmail().toLowerCase();
        this.telefone = empresaUpdateRequest.getTelefone();
        this.enderecoComercial = empresaUpdateRequest.getEnderecoComercial();
    }
}