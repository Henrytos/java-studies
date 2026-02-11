package br.com.alura;
import java.util.UUID;
public class NewOrderMain {

    public static void main(String[] args) {
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
