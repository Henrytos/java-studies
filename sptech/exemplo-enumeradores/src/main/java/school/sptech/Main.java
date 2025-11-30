package school.sptech;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Carta carta1 = new Carta(Simbolo.A, Naipe.ESPADAS);
//        Carta carta2 = new Carta(Simbolo.DEZ, Naipe.PAUS);
//        Carta carta3 = new Carta(Simbolo.A, Naipe.COPAS);
//        Carta carta4 = new Carta(Simbolo.A, Naipe.OUROS);
//
//
//        System.out.println(carta1);
//        System.out.println(carta2);
//        System.out.println(carta3);
//        System.out.println(carta4);
//
//        if(carta1.getSimbolo().compareTo(carta2.getSimbolo()) > 0){ // ja vem pronto
//            System.out.println("CARTA 1 MAIOR que 2");
//        } else {
//            System.out.println("CARTA 1 MENOR que 2");
//        }
//
//        System.out.println(Arrays.toString(Naipe.values()));
//        for (Naipe value : Naipe.values()) { //retorna um array
//            System.out.println(value);
//        }
//
//
//        Naipe naipe = Naipe.valueOf("ESPADAS"); // retorna um enum com base na chave
//        System.out.println(naipe);
//
//        // desafio

        System.out.println("Digite o naipe: ");
        Scanner scanner = new Scanner(System.in);

        String naipeName = scanner.next().toUpperCase();

        Naipe naipe = Naipe.valueOf(naipeName);


        if (naipe == null) {
            System.out.println("Naipe invalido");
            System.exit(0);

        }

        System.out.println("Digite o simbolo:");
        String cartaName = scanner.next().toUpperCase();

        Simbolo simbolo = Simbolo.valueOf(cartaName);
        if (simbolo == null) {
            System.out.println("Simbolo invalido");
            System.exit(0);
        }


        Carta carta = new Carta(simbolo, naipe);

        System.out.println(carta);
    }

}
