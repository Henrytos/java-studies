package school.sptech;

public class Exemplo06 {
    public static void main(String[] args) {
        String nome ="henry";
        int idade = 18;
        float peso = 80.0f;
        float altura = 1.799f;

        String mensagem = "Meu nome é %s tenho %d" +
                " tenho %.2fkg e tenho %.2f de altura";
        System.out.format(mensagem, nome,idade, peso,altura);

        System.out.printf("estou com %d".formatted(19));


        String texto = """
                meu nome é %s
            idade %d
                gosto de java e node
                """.formatted(nome, idade);

        System.out.println(texto);
    }
}
