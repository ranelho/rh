package com.rlti.rh.empresa.application.api;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class EmpresaResponse {
    Long idEmpresa;
}
