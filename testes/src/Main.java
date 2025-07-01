import java.time.LocalDate;

public  class Main {

    public static void main(String[] args) {

        LocalDate currentDate = LocalDate.now();
        BankAccount account  = new BankAccount(1,1,1.0,"henry",currentDate);
        System.out.println(account.username+" - R$"+account.sale);
        account.withdraw(1.0);
        System.out.println(account.username+" - R$"+account.sale);
        account.withdraw(1.0);
        System.out.println(account.username+" - R$"+account.sale);
        Aritimeticos.dividir(10,2);
    }
}