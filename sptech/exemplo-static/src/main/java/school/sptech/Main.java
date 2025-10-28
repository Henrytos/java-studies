package school.sptech;

public class Main {

    public static void main(String[] args) {
        ContaCorrente c1 = new ContaCorrente("Manoel");
        ContaCorrente c2 = new ContaCorrente("Manoel");

        c1.depositar(100.00);
        c1.depositar(1000.00);
        c2.depositar(500.00);
        c2.depositar(600.00);

        c1.sacar(500.00);

        System.out.println(c1);
        System.out.println(c2);

    }

}
