package br.com.alura;

import java.math.BigDecimal;
import java.util.UUID;

public class NewOrderMain {

    public static void main(String[] args) {
        try (var orderDispatch = new KafkaDispatch<Order>()) {
            try (var emailDispatch = new KafkaDispatch<Email>()) {
                for (int i = 0; i < 10; i++) {
                    var userId = UUID.randomUUID().toString();
                    var orderId = UUID.randomUUID().toString();

                    var value = new BigDecimal(Math.random() * 5000 + 100);

                    var order = new Order(userId, orderId, value);
                    orderDispatch.send("ECOMMERCE_NEW_ORDER_DEV", userId, order);

                    var email = new Email("jhondoe@gmail.com", "thank you for you order!");
                    emailDispatch.send("ECOMMERCE_SEND_EMAIL_DEV", userId, email);
                }
            }
        }
    }
}
