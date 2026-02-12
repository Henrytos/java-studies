package br.com.alura;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

public class KafkaService<T> implements Runnable, Closeable {

    private final KafkaConsumer<String, T> consumer;
    private final ConsumerFunction<T> parse;

    KafkaService(String consumerId, String topic, ConsumerFunction<T> parse, Class<T> type) {
        this(consumerId, parse, type);
        this.consumer.subscribe(Collections.singletonList(topic));
    }

    KafkaService(String consumerId, Pattern topic, ConsumerFunction<T> parse, Class<T> type) {
        this(consumerId, parse, type);
        this.consumer.subscribe(topic);
    }

    private KafkaService(String consumerId, ConsumerFunction<T> parse, Class<T> type) {
        this.consumer = new KafkaConsumer<>(properties(type, consumerId));
        this.parse = parse;
    }

    public void run() {
        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));

            if (!records.isEmpty()) {
                for (ConsumerRecord<String, T> record : records) {
                    this.parse.consume(record);
                }
            }

        }
    }


    private Properties properties(Class<T> type, String consumerId) {
        Properties properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GjsonDeserializer.class.getName());
        properties.setProperty(GjsonDeserializer.TYPE_CONFIG, type.getName());

        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerId);
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");

        return properties;
    }

    @Override
    public void close() {
        this.consumer.close();
    }
}
