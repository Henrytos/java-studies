package domain.delivery.repositories;

import domain.delivery.entities.Recipient;

import java.util.UUID;

public interface RecipientsRepository {
    void create(Recipient recipient);
    boolean exist(UUID id);

}
