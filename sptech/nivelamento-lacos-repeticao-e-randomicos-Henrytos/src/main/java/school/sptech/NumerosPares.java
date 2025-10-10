package school.sptech;

public class NumerosPares {
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <= 40; i+=2) {
            stringBuilder.append(i).append(System.lineSeparator());
        }

        System.out.println(stringBuilder);
    }
}
