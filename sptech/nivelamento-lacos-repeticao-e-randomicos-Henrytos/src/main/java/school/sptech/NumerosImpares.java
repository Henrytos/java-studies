package school.sptech;

public class NumerosImpares {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= 89; i+=2) {
            stringBuilder.append(i).append(System.lineSeparator());
        }

        System.out.println(stringBuilder);
    }
}
