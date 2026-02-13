package br.com.alura;

import java.math.BigDecimal;
import java.util.UUID;

public class NewOrderMain {

    public static void main(String[] args) {
        try (var orderDispatch = new KafkaDispatch<Order>()) {
            try (var emailDispatch = new KafkaDispatch<Email>()) {
                for (int i = 0; i < 10; i++) {
                    String subject = String.valueOf(Math.floor(Math.random() * 200 + 100)).concat("@gmail.com");
                    var orderId = UUID.randomUUID().toString();

                    var value = new BigDecimal(Math.random() * 5000 + 100);

                    var order = new Order(orderId, value, subject);
                    orderDispatch.send("ECOMMERCE_NEW_ORDER_DEV", subject, order);

                    var email = new Email(subject, "thank you for you order!");
                    emailDispatch.send("ECOMMERCE_SEND_EMAIL_DEV", subject, email);
                }
            }
        }
    }
}
