package com.rlti.rh.document.infra;

import com.rlti.rh.document.domain.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeJpaRepository extends JpaRepository<DocumentType, Long>{
}
