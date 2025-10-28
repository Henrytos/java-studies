package school.sptech;

import java.util.concurrent.ThreadLocalRandom;

public class ContaCorrente {

    public static Integer contasAbertas = 0;

    private final String numero;
    private final String titular;
    private Double saldo;

    public ContaCorrente() {
        numero = String.valueOf(ThreadLocalRandom.current().nextInt(1000, 10000));
        titular = "";
        this.saldo = 0.0;
        contasAbertas++;
    }

    public ContaCorrente(String titular) {
        this();
        this.saldo = 0.0;
    }

    public void depositar(Double valor) {
        if (valor == null || valor <= 0) {
            throw new IllegalStateException("Não é possível depositar valor menor que R$0,00");
        }

        this.saldo += valor;
    }

    public void sacar(Double valor) {
        if (valor <= 0) {
            throw new IllegalStateException("Não é possível sacar valor negativo");
        }

        if (valor == null || valor > this.saldo) {
            throw new IllegalStateException("Não é possível sacar valor maior que R$" + valor);
        }

        this.saldo -= valor;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
               "numero='" + numero + '\'' +
               ", titular='" + titular + '\'' +
               ", contasAbertas='" + contasAbertas + '\'' +
               ", saldo=" + saldo +
               '}';
    }
}
