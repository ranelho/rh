package rlti.com.rh.utils.email;

import java.io.IOException;

public interface EmailService {
    void enviarEmail(String nomeCompleto, String email, String mensagem);
}
