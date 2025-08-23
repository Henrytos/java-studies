package threads;

import java.math.BigDecimal;

public class BankExample {

    public static void main(String[] args) {
        Bank bank = new Bank(100);

        Thread thread1  = new Thread(()->{
            bank.withdraw(new BigDecimal(100));
        });

        Thread thread2  = new Thread(()->{
            bank.withdraw(new BigDecimal(100));
        });

        thread1.start();
        thread2.start();

        System.out.println(bank.getValue());

    }

}
