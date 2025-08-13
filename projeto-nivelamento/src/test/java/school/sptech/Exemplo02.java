package school.sptech;

public class Exemplo02 {
    public static void main(String[] args) {
        Integer idade = 18;

        if(idade >= 18){
            System.out.println("Pode dirigir");
        } else if (idade >= 16) {
            System.out.println("Pode tirar carteira");
        } else {
            System.out.println("Não Pode dirigir");

        }
    }
}
