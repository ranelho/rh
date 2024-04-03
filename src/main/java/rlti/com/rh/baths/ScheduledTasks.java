package rlti.com.rh.baths;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rlti.com.rh.funcionario.service.FuncionarioService;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {

    private final FuncionarioService funcionarioService;

    //@Scheduled(cron = "*/5 * * * * *", zone = "America/Sao_Paulo")
    @Scheduled(cron = "0 8 * * * *", zone = "America/Sao_Paulo")
    public void runVerificaAniversarioBath() {
        log.info("ScheduledTasks.runVerificaAniversarioBath");
        funcionarioService.verificaAniversarioBath();
    }
}
