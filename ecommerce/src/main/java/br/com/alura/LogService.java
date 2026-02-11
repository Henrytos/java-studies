package br.com.alura;

import br.com.alura.utils.Logger;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.regex.Pattern;

public class LogService {

    public static void main(String[] args) {
        LogService logService = new LogService();

        try (KafkaService kafkaService = new KafkaService(LogService.class.getSimpleName(), Pattern.compile("ECOMMERCE.*"), logService::parse)) {
            kafkaService.run();
        }
    }


    public void parse(ConsumerRecord<String, String> record) {
        var mensagem = "Topico=" + record.topic() + " KEY=" + record.key() + " VALUE=" + record.value() + " TIMESTAMP=" + record.timestamp() + " PARTITION=" + record.partition();
        Logger.getInstance().info(mensagem);
    }
}
