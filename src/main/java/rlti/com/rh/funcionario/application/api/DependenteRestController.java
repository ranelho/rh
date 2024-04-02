package rlti.com.rh.funcionario.application.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import rlti.com.rh.funcionario.application.request.DependenteRequest;
import rlti.com.rh.funcionario.service.DependenteService;

@RestController
@RequiredArgsConstructor
public class DependenteRestController implements DependenteApi {
    private final DependenteService dependenteService;

    @Override
    public void newDependenteFuncionario(String cpfFuncionario, DependenteRequest request) {
        dependenteService.newDependenteFuncionario(cpfFuncionario, request);
    }

    @Override
    public void deleteDependenteFuncionario(String cpfDependente) {
        dependenteService.deleteDependenteFuncionario(cpfDependente);
    }
}
