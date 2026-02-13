package br.com.alura;

import java.math.BigDecimal;
import java.util.Comparator;

public class Order {

    private final String orderId, email;
    private final BigDecimal amount;

    public Order(String orderId, String email, BigDecimal amount) {
        this.orderId = orderId;
        this.email = email;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", email='" + email + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getEmail() {
        return this.email;
    }
}
