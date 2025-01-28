package domain.delivery.use_cases;

import domain.delivery.entities.ORDER_STATUS;
import domain.delivery.entities.Order;
import domain.delivery.repositories.DeliveryAddressRepository;
import domain.delivery.repositories.DeliveryMansRepository;
import domain.delivery.repositories.OrdersRepository;
import domain.delivery.repositories.RecipientsRepository;

import java.util.UUID;

public class RegisterOrderUseCase {
    private final OrdersRepository ordersRepository;
    private final DeliveryMansRepository deliveryMansRepository;
    private final RecipientsRepository recipientsRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    public RegisterOrderUseCase(
            OrdersRepository ordersRepository,
            DeliveryMansRepository deliveryMansRepository,
            RecipientsRepository recipientsRepository,
            DeliveryAddressRepository deliveryAddressRepository
    ) {
        this.ordersRepository = ordersRepository;
        this.deliveryMansRepository = deliveryMansRepository;
        this.recipientsRepository = recipientsRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    public void execute(ORDER_STATUS status, UUID deliveryManId, UUID recipientId, UUID deliveryAddressId) throws Exception {

        boolean deliveryManDoesNotExists = !this.deliveryMansRepository.exist(deliveryManId);
        if (deliveryManDoesNotExists) {
            throw new Exception("delivery man man does not exist");
        }

        boolean recipientDoesNotExist = !this.recipientsRepository.exist(recipientId);
        if (recipientDoesNotExist) {
            throw new Exception("recipient does not exist");
        }

        boolean deliveryAddressDOesNotExist = !this.deliveryAddressRepository.exist(deliveryAddressId);
        if(deliveryAddressDOesNotExist){
            throw new Exception("delivery address not exist");
        }

        Order order = new Order(ORDER_STATUS.cancel, deliveryManId, recipientId, deliveryAddressId);
        this.ordersRepository.create(order);
    }
}
