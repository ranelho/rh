package com.rlti.rh.utils.email;

public record EmailRequest(String remetente, String to, String subject, String body) {
}
