package br.com.alura;

import br.com.alura.utils.Logger;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class CreateUserService {

    private final Connection connection;

    public CreateUserService() throws SQLException {
        String url = "jdbc:sqlite:users_database.sql";
        this.connection = DriverManager.getConnection(url);

        connection.createStatement().execute("""
                create table users(
                    uuid varchar(200) primary key,
                    email varchar(200)
                );
                """);
    }

    public static void main(String[] args) throws SQLException {
        var createUserService = new CreateUserService();

        try (var service = new KafkaService<Order>(CreateUserService.class.getSimpleName(), "ECOMMERCE_NEW_ORDER_DEV", createUserService::parse, Order.class, Map.of())) {
            service.run();
        }
    }

    public void parse(ConsumerRecord<String, Order> record) throws SQLException {
        var order = record.value();

        if (isNewUser(order.getEmail())) {
            insertUser(order.getEmail());
        } else {
            Logger.getInstance().warn("Nao foi possivel criar usuario com email: "+order.getEmail());
        }

    }

    private boolean isNewUser(String email) throws SQLException {
        var exists = connection.prepareStatement("""
                    select uuid from users where email = ? limit 1
                """);
        exists.setString(1, email);
        var results = exists.executeQuery();

        return !results.next();
    }

    private void insertUser(String email) throws SQLException {
        var query = connection.prepareStatement("""
                    insert into users (uuid, email) values(?, ?)
                """);

        query.setString(1, UUID.randomUUID().toString());
        query.setString(2, email);

        query.execute();

        var mensagem = "Criando novo usuario com email: " + email;
        Logger.getInstance().info(mensagem);
    }
}
