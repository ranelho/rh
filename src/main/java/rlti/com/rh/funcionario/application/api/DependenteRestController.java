package rlti.com.rh.funcionario.application.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import rlti.com.rh.funcionario.application.api.request.DependenteRequest;
import rlti.com.rh.funcionario.service.DependenteService;

@RestController
@RequiredArgsConstructor
public class DependenteRestController implements DependenteApi {
    private final DependenteService dependenteService;

    @Override
    public void novoDependente(String cpfFuncionario, DependenteRequest request) {
        dependenteService.novoDependente(cpfFuncionario, request);
    }

    @Override
    public void removerDependente(String cpfDependente) {
        dependenteService.removerDependente(cpfDependente);
    }
}
