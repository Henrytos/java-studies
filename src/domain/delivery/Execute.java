package domain.delivery;

import domain.delivery.entities.DeliveryAddress;
import domain.delivery.entities.DeliveryMan;
import domain.delivery.entities.ORDER_STATUS;
import domain.delivery.entities.Recipient;
import domain.delivery.use_cases.RegisterOrderUseCase;
import domain.delivery.use_cases.in_memory_repositories.InMemoryDeliveryAddressRepository;
import domain.delivery.use_cases.in_memory_repositories.InMemoryDeliveryMansRepository;
import domain.delivery.use_cases.in_memory_repositories.InMemoryOrdersRepository;
import domain.delivery.use_cases.in_memory_repositories.InMemoryRecipientsRepository;

public class Execute {

    public static void main(String[] args) {
        InMemoryOrdersRepository inMemoryOrdersRepository = new InMemoryOrdersRepository();
        InMemoryDeliveryMansRepository inMemoryDeliveryMansRepository = new InMemoryDeliveryMansRepository();
        InMemoryRecipientsRepository inMemoryRecipientsRepository = new InMemoryRecipientsRepository();
        InMemoryDeliveryAddressRepository inMemoryDeliveryAddressRepository = new InMemoryDeliveryAddressRepository();

        DeliveryMan deliveryMan = new DeliveryMan("fulano", "fulano@gmail.com", "senha-fulano");
        inMemoryDeliveryMansRepository.create(deliveryMan);

        Recipient recipient = new Recipient("ciclano", "ciclano@gmail.com", "senha-ciclano");
        inMemoryRecipientsRepository.create(recipient);

        DeliveryAddress deliveryAddress = new DeliveryAddress("sp", "sp", "rua do ciclano", "01");
        inMemoryDeliveryAddressRepository.create(deliveryAddress);

        RegisterOrderUseCase registerOrderUseCase = new RegisterOrderUseCase(
                inMemoryOrdersRepository,
                inMemoryDeliveryMansRepository,
                inMemoryRecipientsRepository,
                inMemoryDeliveryAddressRepository);

        try {
            registerOrderUseCase.execute(ORDER_STATUS.pending, deliveryMan.getId(), recipient.getId(), deliveryAddress.getId());
            System.out.println("success in creating an order");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("success in executing the use case");
        }
    }
}
