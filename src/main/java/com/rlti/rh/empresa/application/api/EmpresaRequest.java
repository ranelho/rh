package com.rlti.rh.empresa.application.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;

public record EmpresaRequest(
        @NotBlank String razaoSocial,
        @NotBlank String nomeFantasia,
        @NotBlank @CNPJ String cnpj,
        @NotBlank String nomeAdministrador,
        @NotBlank String inscricaoMunicipal,
        @NotBlank String areaAtuacao,
        @NotNull LocalDate dataAbertura,
        @NotBlank @Email String email,
        @Pattern(regexp = "^\\(\\d{2}\\)\\d{5}-\\d{4}$") String telefone,
        @NotNull String enderecoComercial,
        @NotNull Boolean aceitaTermos
) {
}
