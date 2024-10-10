package com.rlti.rh.document.infra;

import com.rlti.rh.document.domain.DocumentCargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CagoDocumentJpaRepository extends JpaRepository<DocumentCargo, Long>{
}
