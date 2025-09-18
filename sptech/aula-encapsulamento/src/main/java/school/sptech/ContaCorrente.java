package school.sptech;

import java.util.concurrent.ThreadLocalRandom;

public class ContaCorrente {

    private String numero;
    private String titular;
    private String telefone;
    private Double saldo;
    private String email;

    /*
     * public: Acesso global; qualquer classe pode acessar
     * private: Acesso restrito à própria classe
     * protected: Acesso dentro do mesmo pacote e por subclasses
     * default: Acesso apenas dentro do mesmo pacote
     * */

    public ContaCorrente() {
    }


    public ContaCorrente(String titular, String telefone) {
        numero = "" + ThreadLocalRandom.current().nextInt(1000, 2001);
        saldo = 0.0;
        this.titular = titular;
        this.telefone = telefone;
    }

    public ContaCorrente(String titular, String telefone, String email) {
        this(titular, telefone); // precisa ser primeira linha
        this.email = email;
    }

    public void depositar(Double valorDeposito) {
        if (valorDeposito == null || valorDeposito <= 0) {
            System.out.println("erro ao depositora, valor negativo");
            return;
        }

        saldo += valorDeposito;
        System.out.println("deposito realizado com sucesso");
    }

    public Double sacar(Double valorSacar) {
        if (valorSacar == null || valorSacar < 1) {
            System.out.println("erro ao sacar o dinheiro, valor invalido ou nulo");
            return 0.0;
        }

        if (valorSacar > saldo) {
            System.out.println("Saldo insuficiente");
            return 0.0;
        }

        saldo -= valorSacar;
        System.out.println("Saque efetuado com sucesso");

        return valorSacar;
    }

    public void exibirInformacoes() {
        System.out.println("""
                ---------------------
                Conta Corrente
                ---------------------
                Número: %s
                Titular: %s
                Telefone %s
                Saldo: R$ %.2f
                Email: %s
                """.formatted(numero, titular, telefone, saldo, email));
    }

    public Double getSaldo() {
        return saldo;
    }


    public String getNumero() {
        return numero;
    }


    public String getTitular() {
        return titular;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}