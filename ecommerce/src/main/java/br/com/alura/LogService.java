package br.com.alura;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Properties;
import java.util.regex.Pattern;

public class LogService {

    public static void main(String[] args) {

        var consumer = new KafkaConsumer<>(properties());

        consumer.subscribe(Pattern.compile("ECOMMERCE.*"));

        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));


            if (!records.isEmpty()) {
                for (ConsumerRecord<Object, Object> record : records) {
                    var mensagem = "Topico=" + record.topic() + " KEY=" + record.key() + " VALUE=" + record.value() + " TIMESTAMP=" + record.timestamp();
                    Logger.getInstance().info(mensagem);

                }
            }
        }

    }

    public static Properties properties() {
        Properties properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, LogService.class.getSimpleName());

        return properties;
    }

}
