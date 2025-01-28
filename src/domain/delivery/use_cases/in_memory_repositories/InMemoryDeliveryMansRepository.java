package domain.delivery.use_cases.in_memory_repositories;

import domain.delivery.entities.DeliveryMan;
import domain.delivery.repositories.DeliveryMansRepository;

import java.util.ArrayList;
import java.util.UUID;

public class InMemoryDeliveryMansRepository implements DeliveryMansRepository {

    protected final ArrayList<DeliveryMan> items = new ArrayList<DeliveryMan>();

    @Override
    public void create(DeliveryMan deliveryMan) {
        this.items.add(deliveryMan);

    }

    @Override
    public boolean exist(UUID id) {
        boolean deliveryManDoesExist = false;

        for (DeliveryMan deliveryMan : this.items) {

            if (deliveryMan.getId() == id) {
                deliveryManDoesExist = true;
                return deliveryManDoesExist;
            }

        }

        return deliveryManDoesExist;
    }
}
