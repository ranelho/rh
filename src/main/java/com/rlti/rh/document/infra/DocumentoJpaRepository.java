package com.rlti.rh.document.infra;

import com.rlti.rh.document.domain.FileReference;
import com.rlti.rh.funcionario.domain.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoJpaRepository extends JpaRepository<FileReference, Long>{
    List<FileReference> findAllByMatricula(Matricula matricula);
}
