package rlti.com.rh;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "RH - Calculo Folha de Pagamento API", version = "1.0.0", description = "Folha de Pagamento"),
		servers = {@Server(url = "/rh/api", description = "Default Server URL")})
public class SistemaDeRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeRhApplication.class, args);
	}

}
