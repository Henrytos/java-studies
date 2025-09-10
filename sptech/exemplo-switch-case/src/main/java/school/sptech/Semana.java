package school.sptech;

import java.util.HashMap;
import java.util.Scanner;

public class Semana {

    public static void main(String[] args) {

        HashMap<Integer, String> mapWeek = new HashMap<>();

        mapWeek.put(1, "domingo");
        mapWeek.put(2, "segunda feira");
        mapWeek.put(3, "terça feira");
        mapWeek.put(4, "quarta feira");
        mapWeek.put(5, "quinta feira");
        mapWeek.put(6, "sexta feira");
        mapWeek.put(7, "sabado");


        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite u numero de 1-7");
        Integer target = scanner.nextInt();

        if(!mapWeek.containsKey(target)) {
            System.out.println("numero invalido");
            return;
        }

        System.out.println(mapWeek.get(target));
    }

}
