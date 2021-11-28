package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.Service.PaymentServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Delivery;
import bg.coffeshop.coffeeShop.model.entity.Payment;
import bg.coffeshop.coffeeShop.repository.PaymentRepository;
import bg.coffeshop.coffeeShop.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final ModelMapper modelMapper;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(ModelMapper modelMapper, PaymentRepository paymentRepository) {
        this.modelMapper = modelMapper;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(PaymentServiceModel paymentServiceModel) {
        Payment payment = this.modelMapper.map(paymentServiceModel, Payment.class);
        this.paymentRepository.saveAndFlush(payment);
        return payment;
    }
}
