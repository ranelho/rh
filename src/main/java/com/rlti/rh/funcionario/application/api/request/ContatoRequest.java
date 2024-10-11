package com.rlti.rh.funcionario.application.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record ContatoRequest(
        @Email
        String email,
        @Pattern(regexp = "^\\(\\d{2}\\)\\d{4,5}\\-\\d{4}$", message = "Telefone inválido")
        String telefone,
        String rua,
        String bairro,
        String cidade,
        @Pattern(regexp = "^(AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO)$")
        String estado,
        @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$", message = "CEP inválido")
        String cep,
        String numero,
        String complemento,
        String pais
) {
}