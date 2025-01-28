package domain.delivery.repositories;

import domain.delivery.entities.DeliveryMan;

import java.util.UUID;

public interface DeliveryMansRepository {
    void create(DeliveryMan deliveryMan);
    boolean exist (UUID id);

}
