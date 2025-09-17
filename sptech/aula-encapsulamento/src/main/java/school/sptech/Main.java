package school.sptech;

public class Main {

    public static void main(String[] args) {
        ContaCorrente contaCorrente = new ContaCorrente("henry", "11 96873-4503");
        contaCorrente.exibirInformacoes();

        ContaCorrente contaCorrente2 = new ContaCorrente(
                "murilo",
                "11 96873-4503",
                "murilo.barbosa@sptech.school");
        contaCorrente2.exibirInformacoes();

        System.out.println(contaCorrente.getSaldo());
        System.out.println(contaCorrente2.getSaldo());
    }

}