package com.rlti.rh.funcionario.application.api.response;

import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.document.api.response.CargoDocumentResponse;
import com.rlti.rh.document.domain.FileReference;
import com.rlti.rh.funcionario.domain.Matricula;

import java.util.List;

public record DocumentsFuncionarioResponse(
        String nomeFuncionario,
        String cargo,
        List<CargoDocumentResponse> documentsExidos,
        List<FileReferenceResponse> documentos) {

    public DocumentsFuncionarioResponse(Matricula matr, Contrato contrato, List<FileReference> fileReferences) {
        this(
                matr.getFuncionario().getNomeCompleto(),
                contrato.getCargo().getNomeCargo(),
                CargoDocumentResponse.converter(contrato.getCargo().getDocumentCargos()),
                FileReferenceResponse.converte(fileReferences)
        );
    }
}
