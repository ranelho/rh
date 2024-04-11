package com.rlti.rh.utils;

import com.rlti.rh.funcionario.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class Utils {

    private final MatriculaRepository matriculaRepository; // Injetando o MatriculaRepository

    public String gerarMatricula() {
        int lastMatricula = matriculaRepository.lastMatricula();
        int novaMatricula = lastMatricula + 1;

        String novaMatriculaStr = String.valueOf(novaMatricula);

        // Certifique-se de que a nova matrícula tenha 6 dígitos preenchendo com zeros à esquerda, se necessário
        if (novaMatriculaStr.length() < 6) {
            novaMatriculaStr = StringUtils.leftPad(novaMatriculaStr, 6, "0");
        }

        return novaMatriculaStr;
    }


    public static String formatText(String name) {
        // Verifica se o nome é vazio ou nulo
        if (name == null || name.isEmpty()) {
            return "";
        }
        // Divide o nome em palavras
        String[] words = name.split("\\s+");
        StringBuilder formattedName = new StringBuilder();

        for (String word : words) {
            // Verifica se a palavra é um dos artigos "da", "do"
            if (!word.equalsIgnoreCase("da") && !word.equalsIgnoreCase("do")
                    && !word.equalsIgnoreCase("dos")) {
                // Converte a primeira letra para maiúscula e as demais para minúscula
                word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            }
            // Adiciona a palavra ao nome formatado
            formattedName.append(word).append(" ");
        }
        // Remove o espaço em branco extra no final e retorna o nome formatado
        return formattedName.toString().trim();
    }

    public static int calcularIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now(); // Obtém a data atual
        return Period.between(dataNascimento, hoje).getYears(); // Calcula a diferença em anos entre as datas
    }
    public static String formatarMoeda(BigDecimal valor) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat formato = new DecimalFormat("R$ #,##0.00", simbolos);
        return formato.format(valor);
    }
}
