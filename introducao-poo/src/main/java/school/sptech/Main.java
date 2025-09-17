package school.sptech;

public class Main {
    public static void main(String[] args) {
        BilheteUnico bilheteUnico = new BilheteUnico();

        bilheteUnico.numero = "01";
        bilheteUnico.nomeUsuario = "fulano";
        bilheteUnico.cor = "azul";
        bilheteUnico.saldo = 100.00;
        bilheteUnico.idoso = false;
        bilheteUnico.estudante = false;

        bilheteUnico.printarInformacoes();

        BilheteUnico bilheteUnico2 = new BilheteUnico();

        bilheteUnico2.numero = "02";
        bilheteUnico2.nomeUsuario = "deutrano";
        bilheteUnico2.cor = "preto";
        bilheteUnico2.saldo = 0.00;
        bilheteUnico2.idoso = true;
        bilheteUnico2.estudante = false;

        bilheteUnico2.printarInformacoes();
    }
}