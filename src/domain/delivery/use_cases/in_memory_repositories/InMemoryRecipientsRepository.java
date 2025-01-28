package domain.delivery.use_cases.in_memory_repositories;

import domain.delivery.entities.Recipient;
import domain.delivery.repositories.RecipientsRepository;

import java.util.ArrayList;
import java.util.UUID;

public class InMemoryRecipientsRepository implements RecipientsRepository {

    protected ArrayList<Recipient> items = new ArrayList<>();

    @Override
    public void create(Recipient recipient) {
        this.items.add(recipient);
    }

    @Override
    public boolean exist(UUID id) {
        boolean recipientDoesExist = false;

        for (Recipient recipient : this.items) {

            if (recipient.getId() == id) {
                recipientDoesExist = true;
                return recipientDoesExist;
            }
        }

        return recipientDoesExist;
    }
}
