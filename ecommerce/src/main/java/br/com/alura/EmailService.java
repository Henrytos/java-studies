package br.com.alura;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class EmailService {

    public static void main(String[] args) {
        var consumer = new KafkaConsumer<>(properties());

        consumer.subscribe(Collections.singletonList("ECOMMERCE_SEND_EMAIL_DEV"));

        while (true) {

            var records = consumer.poll(Duration.ofMillis(100));

            if (!records.isEmpty()) {

                for (ConsumerRecord<Object, Object> record : records) {
                    System.out.println("----------------");
                    System.out.println("KEY=" + record.key() + " - VALUE:" + record.value() + " - PARTITION:" + record.partition() + " - OFFSET:" + record.offset());

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("Processo de email realizado com sucesso");
                }
            }

        }
    }


    public static Properties properties(){
        Properties properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, EmailService.class.getSimpleName());

        return properties;
    }

}
