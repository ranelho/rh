package rlti.com.rh.utils;

import java.util.Random;

public class MatriculaGenerator {
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
}
