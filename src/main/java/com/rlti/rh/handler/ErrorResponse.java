package com.rlti.rh.handler;

import lombok.Value;

@Value
public class ErrorResponse {
    int codigo;
    String mensagem;
}
