import java.util.Scanner;

public class ClasessEssenciais {
    public static void main(String[] args) {
        String texto = "henry;franz;18;1.70";
        Scanner scanner = new Scanner(texto);
        scanner.useDelimiter(";");

        String nome = "";
        String sobrenome = "";
        Integer idade = 0;
        Double altura = 0.0;

        int index = 0;
        while (index < 4){
            if(index == 0){
                nome = scanner.next();
            }else if(index == 1){
                sobrenome = scanner.next();
            } else if (index == 2) {
                idade = Integer.valueOf(scanner.next());
            }else{
                altura = Double.valueOf(scanner.next());
            }

            index++;
        }

        System.out.format("olá %s %s, você tem %d anos e mede %.2f", nome, sobrenome, idade, altura);

    }
}

