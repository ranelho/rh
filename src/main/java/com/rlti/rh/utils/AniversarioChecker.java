package com.rlti.rh.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import com.rlti.rh.funcionario.domain.Funcionario;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
@Slf4j
public class AniversarioChecker {

    public static Map<String, String> verificarAniversario(Funcionario funcionario) {
        Map<String, String> detalhesFuncionario = new HashMap<>();
        LocalDate dataAtual = LocalDate.now();

        if (funcionario.getDataNascimento().getMonth() == dataAtual.getMonth() &&
                funcionario.getDataNascimento().getDayOfMonth() == dataAtual.getDayOfMonth()) {
            int anos = dataAtual.getYear() - funcionario.getDataNascimento().getYear();
            detalhesFuncionario.put("nomeCompleto", funcionario.getNomeCompleto());
            detalhesFuncionario.put("dataNascimento", funcionario.getDataNascimento().toString());
            // Verifica se o objeto de contato não é nulo antes de acessar o método getEmail()
            detalhesFuncionario.put("email", funcionario.getContato() != null ? funcionario.getContato().getEmail() : null);
            detalhesFuncionario.put("mensagem", "Parabéns, " + funcionario.getNomeCompleto() + "! Hoje é o seu aniversário e você está fazendo " + anos + " anos!");
        }
        return detalhesFuncionario;
    }
}
