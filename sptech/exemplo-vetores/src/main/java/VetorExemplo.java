import java.util.Arrays;

public class VetorExemplo {
    public static void main(String[] args) {
        int[] numbers = new int[5];
        numbers[2] = 10;

        System.out.println(numbers); // hash code
        System.out.println(Arrays.toString(numbers));
        System.out.println(numbers[2]);

        Integer[] idades = {23, 1, 3};
        idades[2] = 10;
        System.out.println(Arrays.toString(idades));
        System.out.println(idades[2]);

        int[] idades2 = new int[] {32,10};

        for (int i : idades2) {
            System.out.println(i);
        }

        String[] frutas = new String[3];
        frutas[0] = "mamão";

        System.out.println(Arrays.toString(frutas));


        boolean[] likes = new boolean[10];
        likes[0] = false;
        System.out.println(Arrays.toString(likes));

    }
}
