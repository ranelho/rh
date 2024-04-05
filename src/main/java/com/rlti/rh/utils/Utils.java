package com.rlti.rh.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class Utils {

    private static final Random random = new Random();

    // Private constructor to hide the implicit public one
    private Utils() {
        throw new AssertionError(); // This prevents instantiation even from within the class
    }

    // Method to generate a registration number
    public static String gerarMatricula() {
        // Define the length of the desired registration number
        int tamanhoMatricula = 6;

        // Create a StringBuilder to store the generated registration number
        StringBuilder matricula = new StringBuilder();

        // Generate each digit of the registration number
        for (int i = 0; i < tamanhoMatricula; i++) {
            // Generate a random number between 0 and 9
            int digito = random.nextInt(10);
            // Add the digit to the registration number
            matricula.append(digito);
        }

        // Return the generated registration number as a string
        return matricula.toString();
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
}
