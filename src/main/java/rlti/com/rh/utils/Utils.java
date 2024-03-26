package rlti.com.rh.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class Utils {

    public static String gerarMatricula() {
        // Inicializa um objeto Random
        Random random = new Random();

        // Define o tamanho da matrícula desejada
        int tamanhoMatricula = 6;

        // Cria uma string para armazenar a matrícula gerada
        StringBuilder matricula = new StringBuilder();

        // Gera cada dígito da matrícula
        for (int i = 0; i < tamanhoMatricula; i++) {
            // Gera um número aleatório entre 0 e 9
            int digito = random.nextInt(10);
            // Adiciona o dígito à matrícula
            matricula.append(digito);
        }

        // Retorna a matrícula gerada como uma string
        return matricula.toString();
    }

    public static String formatName(String name) {
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
