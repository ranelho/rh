package com.rlti.rh.contrato.application.response;

import com.rlti.rh.contrato.domain.Contrato;

import java.time.LocalDate;
import java.util.List;

public record ContratosAtivosResponse(
        Long numeroContrato,
        LocalDate dataAdmissao,
        String setor,
        String cargo,
        String matricula,
        String NomeCompleto,
        String cpf,
        LocalDate dataNascimento,
        String telefone,
        String email
) {
    public ContratosAtivosResponse (Contrato contrato){
        this(
                contrato.getIdContrato(),
                contrato.getDataAdmissao(),
                contrato.getSetor().getNomeSetor(),
                contrato.getCargo().getNomeCargo(),
                contrato.getMatricula().getNumeroMatricula(),
                contrato.getMatricula().getFuncionario().getNomeCompleto(),
                contrato.getMatricula().getFuncionario().getCpf(),
                contrato.getMatricula().getFuncionario().getDataNascimento(),
                contrato.getMatricula().getFuncionario().getContato().getTelefone(),
                contrato.getMatricula().getFuncionario().getContato().getEmail()
        );
    }

    public static List<ContratosAtivosResponse> convert(List<Contrato> contratos) {
        return contratos.stream().map(ContratosAtivosResponse::new).toList();
    }
}
