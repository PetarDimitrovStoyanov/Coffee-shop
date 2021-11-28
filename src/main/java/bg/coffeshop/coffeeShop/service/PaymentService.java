package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.Service.PaymentServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Payment;

public interface PaymentService {
    Payment save(PaymentServiceModel paymentServiceModel);
}
