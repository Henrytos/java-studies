package br.com.alura;

import java.math.BigDecimal;
import java.util.Comparator;

public class Order {

    private final String  orderId;
    private final BigDecimal amount;
    private final String email;

    public Order(String orderId, BigDecimal amount, String email) {
        this.orderId = orderId;
        this.amount = amount;
        this.email = email;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }


    @Override
    public String toString() {
        return "Order{" +
                "  orderId='" + orderId + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getEmail() {
        return this.email;
    }
}
