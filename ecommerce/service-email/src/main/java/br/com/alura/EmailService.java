package br.com.alura;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class EmailService {

    public static void main(String[] args) {
        EmailService emailService = new EmailService();

        try (var kafkaService = new KafkaService<>(EmailService.class.getSimpleName(), "ECOMMERCE_SEND_EMAIL_DEV", emailService::parse, Email.class, Map.of())) {
            kafkaService.run();
        }
    }

    public void parse(ConsumerRecord<String, Email> record) {
        System.out.println("----------------");
        System.out.println("KEY=" + record.key() + " - VALUE:" + record.value() + " - PARTITION:" + record.partition() + " - OFFSET:" + record.offset());
        System.out.println("----------------");
    }
}
