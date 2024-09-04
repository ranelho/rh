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

    private final MatriculaRepository matriculaRepository;

    public String gerarMatricula() {
        int lastMatricula = matriculaRepository.lastMatricula();
        int novaMatricula = lastMatricula + 1;

        String novaMatriculaStr = String.valueOf(novaMatricula);

        if (novaMatriculaStr.length() < 6) {
            novaMatriculaStr = StringUtils.leftPad(novaMatriculaStr, 6, "0");
        }

        return novaMatriculaStr;
    }


    public static String formatText(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        String[] words = name.split("\\s+");
        StringBuilder formattedName = new StringBuilder();
        for (String word : words) {
            String lowerCaseWord = word.toLowerCase();
            if (!lowerCaseWord.equals("da") && !lowerCaseWord.equals("do")
                    && !lowerCaseWord.equals("das") && !lowerCaseWord.equals("dos")) {
                word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            }
            formattedName.append(word).append(" ");
        }
        return formattedName.toString().trim();
    }

    public static int calcularIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        return Period.between(dataNascimento, hoje).getYears();
    }

    public static String formatarMoeda(BigDecimal valor) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat formato = new DecimalFormat("R$ #,##0.00", simbolos);
        return formato.format(valor);
    }
}
