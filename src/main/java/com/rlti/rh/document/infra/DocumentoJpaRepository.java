package com.rlti.rh.document.infra;

import com.rlti.rh.document.domain.FileReference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoJpaRepository extends JpaRepository<FileReference, Long>{
}
