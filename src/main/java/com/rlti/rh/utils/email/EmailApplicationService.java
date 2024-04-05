package com.rlti.rh.utils.email;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class EmailApplicationService implements EmailService {

    private final EmailClient emailClient;
    private static final String PATH_HTML = "src/main/resources/templates/email_template.html";

    public EmailApplicationService(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    @SneakyThrows
    @Override
    public void enviarEmail(String nomeCompleto, String email, String mensagem)  {
        log.info("Envio de email de aniversário para {}", nomeCompleto);
        String htmlContent;
        htmlContent = lerConteudoDoArquivo(PATH_HTML);
        htmlContent = htmlContent.replace("{{nome}}", nomeCompleto);
        EmailRequest emailRequest = new EmailRequest("noreply@rl-ti.com", email,
                nomeCompleto.split(" ")[0] + ", parabéns pelo seu dia. \uD83E\uDD73\uD83C\uDF89\n", htmlContent);
        emailClient.enviarEmail(emailRequest);
    }

    public static String lerConteudoDoArquivo(String caminhoDoArquivo) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(caminhoDoArquivo));
        return new String(bytes);
    }
}
