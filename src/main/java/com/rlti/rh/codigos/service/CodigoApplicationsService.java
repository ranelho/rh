package com.rlti.rh.codigos.service;

import com.rlti.rh.codigos.application.api.CodigoRequest;
import com.rlti.rh.codigos.application.api.CodigoResponse;
import com.rlti.rh.codigos.domain.Codigo;
import com.rlti.rh.handler.APIException;
import com.rlti.rh.horas.repository.CodigoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodigoApplicationsService implements CodigoService {
    private final CodigoRepository codigoRepository;

    @Override
    public CodigoResponse newCodigo(CodigoRequest codigoRequest) {
        Optional<Codigo> optionalCodigo = codigoRepository.findByCodigoOrDescricao(codigoRequest.cod(), codigoRequest.descricao());
        if (optionalCodigo.isPresent()) {
            Codigo codigo = optionalCodigo.get();
            if (Objects.equals(codigo.getCod(), codigoRequest.cod())) {
                throw APIException.build(HttpStatus.BAD_REQUEST, "Código já cadastrado");
            } else if (Objects.equals(codigo.getDescricao(), codigoRequest.descricao())) {
                throw APIException.build(HttpStatus.BAD_REQUEST, "Descrição já cadastrada");
            }
        }
        Codigo codigo = codigoRepository.save(new Codigo(codigoRequest));
        return new CodigoResponse(codigo);
    }

    @Override
    public List<CodigoResponse> getAllCodigos() {
        return codigoRepository.findAll().stream().map(CodigoResponse::new).toList();
    }

    @Override
    public List<CodigoResponse> newCodigos(List<CodigoRequest> requests) {
        List<Codigo> codigos = new ArrayList<>();
        for (CodigoRequest request : requests){
            Codigo codigo = new Codigo(request);
            codigos.add(codigo);
        }
        codigos = codigoRepository.saveAll(codigos);
        return codigos.stream().map(CodigoResponse::new).toList();
    }
}
