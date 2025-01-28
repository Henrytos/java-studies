package domain.delivery.use_cases.in_memory_repositories;

import domain.delivery.entities.DeliveryAddress;
import domain.delivery.repositories.DeliveryAddressRepository;

import java.util.ArrayList;
import java.util.UUID;

public class InMemoryDeliveryAddressRepository implements DeliveryAddressRepository {

    protected ArrayList<DeliveryAddress> items = new ArrayList<DeliveryAddress>();

    @Override
    public void create(DeliveryAddress deliveryAddress) {
        this.items.add(deliveryAddress);
    }

    @Override
    public boolean exist(UUID id) {
        boolean deliveryAddressDoesExist = false;

        for (DeliveryAddress deliveryAddress : this.items) {

            if (deliveryAddress.getId() == id) {
                deliveryAddressDoesExist = true;
                return deliveryAddressDoesExist;
            }

        }

        return deliveryAddressDoesExist;
    }
}
