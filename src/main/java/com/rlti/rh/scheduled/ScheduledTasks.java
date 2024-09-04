package com.rlti.rh.scheduled;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.rlti.rh.funcionario.service.FuncionarioService;


@Component
@Slf4j
public class ScheduledTasks {

    private final FuncionarioService funcionarioService;

    public ScheduledTasks(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Value("${scheduled.birthdayCheck.enabled}")
    private boolean birthdayCheckEnabled;

    @Value("${scheduled.birthdayCheck.cronExpression}")
    private String birthdayCheckCronExpression;

    @Scheduled(cron = "${scheduled.birthdayCheck.cronExpression}", zone = "America/Sao_Paulo")
    public void runVerificaAniversarioBath() {
        if (birthdayCheckEnabled) {
            log.info("ScheduledTasks.runVerificaAniversarioBath");
            try {
                funcionarioService.verificaAniversarioBath();
            } catch (RetryableException e) {
                log.error(e.getMessage());
            }
        } else {
            log.info("Birthday check task is disabled.");
        }
    }
}
