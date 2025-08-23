package threads;

import java.math.BigDecimal;

public class Bank {
    private BigDecimal value;

    Bank(double value){
        this.value = new BigDecimal(value);
    }


    public  void credit(BigDecimal valueCredit){
        System.out.println("credit value = "+ valueCredit.toString());
        this.value = this.value.add(valueCredit);
    }

    public synchronized void withdraw(BigDecimal valueWithdraw){
        if(-1 == this.value.compareTo(valueWithdraw)){
            System.out.println("invalid value");
            return;
        }
        System.out.println("withdraw value = "+ valueWithdraw.toString());
        this.value = this.value.subtract(valueWithdraw);
    }

    public BigDecimal getValue(){
        return this.value;
    }
}
