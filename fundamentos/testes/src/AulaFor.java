package testes.src;

public class AulaFor {

    public static void main(String[] args) {

        for (int carnerinhos = 1; carnerinhos <= 20; carnerinhos++) {
            System.out.println(carnerinhos + " CARNERINHOS...");
        }

        String nomes[] = {"henry", "felipe", "zorzete", "lucas", "gui", "manu"};

        System.out.println("FOR");
        for (int i = 0; i < nomes.length; i++) {
            System.out.println("NOME:" + nomes[i]);
        }

        System.out.println("FOREACH");
        for (String nomeAlvo : nomes) {
            System.out.println("NOME:" + nomeAlvo);
        }
        ;

    }
}
