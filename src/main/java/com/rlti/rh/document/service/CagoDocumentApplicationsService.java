package com.rlti.rh.document.service;

import com.rlti.rh.contrato.domain.Cargo;
import com.rlti.rh.contrato.repository.CargoRepository;
import com.rlti.rh.document.api.request.CagoDocumentRequest;
import com.rlti.rh.document.domain.DocumentCargo;
import com.rlti.rh.document.domain.DocumentType;
import com.rlti.rh.document.repository.CagoDocumentRepository;
import com.rlti.rh.document.repository.DocumentTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CagoDocumentApplicationsService implements CagoDocumentService {
    private final CagoDocumentRepository cagoDocumentRepository;
    private final CargoRepository cargoRepository;
    private final DocumentTypeRepository documentTypeRepository;

    @Override
    public void postCagoDocument(Long idCargo, List<CagoDocumentRequest> cagoDocumentRequest) {
        Cargo cargo = findCargoById(idCargo);
        List<DocumentCargo> documentCargos = DocumentCargo.fromRequestList(
                cargo,
                cagoDocumentRequest,
                this::findDocumentTypeById
        );
        cagoDocumentRepository.saveAll(documentCargos);
    }

    private Cargo findCargoById(Long idCargo) {
        return cargoRepository.findById(idCargo)
                .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado para o id: " + idCargo));
    }

    private DocumentType findDocumentTypeById(Long idDocument) {
        return documentTypeRepository.findById(idDocument)
                .orElseThrow(() -> new EntityNotFoundException("DocumentType não encontrado para o id: " + idDocument));
    }
}
