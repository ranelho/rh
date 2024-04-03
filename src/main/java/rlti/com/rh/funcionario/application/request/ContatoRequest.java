package rlti.com.rh.funcionario.application.request;

public record ContatoRequest(
        String email,
        String telefone,
        String rua,
        String bairro,
        String cidade,
        String estado,
        String cep,
        String numero,
        String complemento,
        String pais
) {
}