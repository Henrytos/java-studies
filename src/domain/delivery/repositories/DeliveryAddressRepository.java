package domain.delivery.repositories;

import domain.delivery.entities.DeliveryAddress;

import java.util.UUID;

public interface DeliveryAddressRepository {
    void create(DeliveryAddress deliveryAddress);
    boolean exist (UUID id);
}
