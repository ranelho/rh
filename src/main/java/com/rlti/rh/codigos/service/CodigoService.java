package com.rlti.rh.codigos.service;

import com.rlti.rh.codigos.application.api.CodigoRequest;
import com.rlti.rh.codigos.application.api.CodigoResponse;

import java.util.List;

public interface CodigoService {
    CodigoResponse newCodigo(CodigoRequest codigoRequest);
    List<CodigoResponse> getAllCodigos();
    List<CodigoResponse> newCodigos(List<CodigoRequest> codigoRequest);
}
