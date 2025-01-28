package domain.delivery.entities;

import java.util.Date;
import java.util.Random;
import java.util.UUID;


public class Order {
    private final UUID id = UUID.randomUUID();
    private  ORDER_STATUS status = ORDER_STATUS.pending;
    private final UUID deliveryManId;
    private final UUID recipientId;
    private final UUID addressId;
    private  Date createdAt;
    private  Date updatedAt = new Date();

    public Order(ORDER_STATUS status, UUID deliveryManId, UUID recipientId, UUID addressId) {
        this.status = status;
        this.deliveryManId = deliveryManId;
        this.recipientId = recipientId;
        this.addressId = addressId;
    }

    private void touch() {
        this.updatedAt = new Date();
    }

}
