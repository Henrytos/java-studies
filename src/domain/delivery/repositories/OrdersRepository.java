package domain.delivery.repositories;

import domain.delivery.entities.Order;

public  interface OrdersRepository {
    void create(Order address);
}
