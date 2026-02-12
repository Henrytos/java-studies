package br.com.alura;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {

    public static void main(String[] args) {
        FraudDetectorService fraudDetectorService = new FraudDetectorService();
        try (var kafkaService = new KafkaService<>(FraudDetectorService.class.getSimpleName(), "ECOMMERCE_NEW_ORDER_DEV", fraudDetectorService::parse, Order.class);
        ) {
            kafkaService.run();
        }
    }

    public void parse(ConsumerRecord<String, Order> record) {

        System.out.println("----------------");
        System.out.println("KEY=" + record.key() + " - VALUE:" + record.value() + " - PARTITION:" + record.partition() + " - OFFSET:" + record.offset());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Processo de pedido finalizado");
    }
}
