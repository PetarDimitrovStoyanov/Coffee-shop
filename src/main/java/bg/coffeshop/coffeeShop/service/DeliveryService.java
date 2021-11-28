package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.Service.DeliveryServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Delivery;

public interface DeliveryService {
    Delivery save(DeliveryServiceModel deliveryServiceModel);
}
