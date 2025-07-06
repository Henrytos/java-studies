package testes.src;

import java.time.LocalDate;

public class Expresoes {

    public static void main(String[] args) {
        String nome = "henry";
        Integer matricula = 211313;
        LocalDate dataNascimento = LocalDate.of(2006, 10, 13);

        String textoFormatado = String.format("Olá %s da mat.:%d nasc.:%s", nome, matricula, dataNascimento);

        System.out.println(textoFormatado);
    }

}
