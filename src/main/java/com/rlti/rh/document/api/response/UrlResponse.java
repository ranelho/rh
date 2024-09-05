package com.rlti.rh.document.api.response;

import lombok.Data;

@Data
public class UrlResponse {
    private String url;
    private long expirationTime;
}
