package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.service.DeliveryServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Delivery;

public interface DeliveryService {
    Delivery save(DeliveryServiceModel deliveryServiceModel);

    String findById(Long id) throws Exception;
}
