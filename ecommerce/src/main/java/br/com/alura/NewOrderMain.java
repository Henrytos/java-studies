package br.com.alura;

import org.apache.kafka.clients.producer.Callback;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callback callback = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }

            System.out.println("TOPICO :: " + data.topic() + " / ofset " + data.offset() + " / timestamp" + data.timestamp());
        };

        try (var kafkaDispatchService = new KafkaDispatch()) {
            try (var emailSend = new KafkaDispatch()) {

                for (int i = 0; i < 10; i++) {
                    var key = UUID.randomUUID().toString();
                    var value = ",1212,1212";

                    kafkaDispatchService.send("ECOMMERCE_NEW_ORDER_DEV", key, value);

                    var email = "thank you for you order!";
                    emailSend.send("ECOMMERCE_SEND_EMAIL_DEV", key, email);
                }
            }
        }
    }


}
