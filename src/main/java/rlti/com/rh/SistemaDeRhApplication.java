package rlti.com.rh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SistemaDeRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeRhApplication.class, args);
	}

}
