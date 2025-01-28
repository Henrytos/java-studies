package domain.delivery.use_cases.in_memory_repositories;

import domain.delivery.entities.Order;
import domain.delivery.repositories.OrdersRepository;

import java.util.ArrayList;

public class InMemoryOrdersRepository implements OrdersRepository {

    protected final ArrayList<Order> items = new ArrayList<Order>();

    @Override
    public void create(Order order) {
        this.items.add(order);
    }

}
