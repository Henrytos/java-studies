package school.sptech;

public class Exemplo04 {

    public static void main(String[] args) {

        // atalho for i
        for (int i = 0; i < 10; i++) {
            System.out.format("hello %s %s", i, System.lineSeparator());
        }

        int contador = 0;

        while (contador<10){
            System.out.println(contador);
            contador++;
        }

        do {
            System.out.println(contador);
            contador++;
        } while (false);
    }

}
