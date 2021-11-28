package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.service.PaymentServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Payment;

public interface PaymentService {
    Payment save(PaymentServiceModel paymentServiceModel);

    String findById(Long id) throws Exception;
}
