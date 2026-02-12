package br.com.alura;

import br.com.alura.utils.Logger;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;
import java.util.regex.Pattern;

public class LogService {

    public static void main(String[] args) {
        LogService logService = new LogService();

        try (var kafkaService = new KafkaService<String>(
                LogService.class.getSimpleName(),
                Pattern.compile("ECOMMERCE.*"),
                logService::parse,
                String.class,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName())
        )){
            kafkaService.run();
        }
    }


    public void parse(ConsumerRecord<String, String> record) {
        var mensagem = "Topico=" + record.topic() + " KEY=" + record.key() + " VALUE=" + record.value() + " TIMESTAMP=" + record.timestamp() + " PARTITION=" + record.partition();
        Logger.getInstance().info(mensagem);
    }
}
