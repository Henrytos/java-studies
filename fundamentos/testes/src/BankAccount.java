package testes.src;

import java.time.LocalDate;

public class BankAccount {
    int numberAccount;
    int numberAgency;
    double sale;
    String username;
    LocalDate LocalDateOfBirth;
    boolean active = true;

    public BankAccount(int numberAccount, int numberAgency, double sale, String username, LocalDate LocalDateOfBirth) {
        this.numberAccount = numberAccount;
        this.numberAgency = numberAgency;
        this.sale = sale;
        this.username = username;
        this.LocalDateOfBirth = LocalDateOfBirth;
    }

    protected void withdraw(double value) throws Error {
        if (value > this.sale) {
            throw new Error("error");
        }

        if (this.sale > value) {
            this.sale = this.sale - value;
        }
    }

    protected void cancellation(String justification) {
        System.out.println("Parabéns pelo cancelamento, efetuado com sucesso");
        System.out.println("Seu motivo foi:" + justification);
    }

    protected double consult() {
        System.out.println("Seu saldo atual eh:" + this.sale);
        return this.sale;
    }

    protected void makeTransfer(
            BankAccount accountToTransfer,
            double transferValue
    ) {
        accountToTransfer.sale += transferValue;
    }
}
