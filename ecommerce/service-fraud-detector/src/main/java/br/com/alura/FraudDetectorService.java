package br.com.alura;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.math.BigDecimal;
import java.util.Map;

public class FraudDetectorService {

    public static void main(String[] args) {
        FraudDetectorService fraudDetectorService = new FraudDetectorService();
        try (var kafkaService = new KafkaService<>(FraudDetectorService.class.getSimpleName(), "ECOMMERCE_NEW_ORDER_DEV", fraudDetectorService::parse, Order.class, Map.of());) {
            kafkaService.run();
        }
    }

    private final KafkaDispatch<Order> orderKafkaDispatch = new KafkaDispatch<>();

    public void parse(ConsumerRecord<String, Order> record) {
        System.out.println("----------------");
        System.out.println("KEY=" + record.key() + " - VALUE:" + record.value() + " - PARTITION:" + record.partition() + " - OFFSET:" + record.offset());

        var order = record.value();
        if (isFraud(order)) {
            System.out.println("Order is fraud!!!!");
            orderKafkaDispatch.send("ECOMMERCE_ORDER_REJECT", order.getEmail(), order);
        } else {
            System.out.println("Approved: " + order);
            orderKafkaDispatch.send("ECOMMERCE_ORDER_APPROVED", order.getEmail(), order);
        }

        System.out.println("Processo de pedido finalizado");
    }

    public boolean isFraud(Order order) {
        return order.getAmount().compareTo(new BigDecimal("4500")) >= 0;
    }
}
