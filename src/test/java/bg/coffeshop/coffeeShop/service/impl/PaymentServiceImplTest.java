package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.entity.Payment;
import bg.coffeshop.coffeeShop.model.service.PaymentServiceModel;
import bg.coffeshop.coffeeShop.repository.PaymentRepository;
import bg.coffeshop.coffeeShop.service.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PaymentServiceImplTest {

    private ModelMapper modelMapper = new ModelMapper();
    Payment payment = new Payment();

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    public void init() {
        paymentService = new PaymentServiceImpl(modelMapper, paymentRepository);
        payment.setPaymentType("card");
        payment.setOwner("owner");
        payment.setCardNumber("0000 0000 0000 0000");
        payment.setCvv("cvv");
        payment.setExpirationMonth("January");
        payment.setExpirationYear(12);
        when(paymentRepository.saveAndFlush(payment))
                .thenReturn(payment);
        when(paymentRepository.findById(payment.getId()))
                .thenReturn(Optional.of(payment));

    }

    @Test
    public void save(){
        PaymentServiceModel paymentServiceModel = new PaymentServiceModel();
        paymentServiceModel.setPaymentType("card");
        paymentServiceModel.setOwner("owner");
        paymentServiceModel.setCardNumber("0000 0000 0000 0000");
        paymentServiceModel.setCvv("cvv");
        paymentServiceModel.setExpirationMonth("January");
        paymentServiceModel.setExpirationYear(12);

        Payment actual = paymentService.save(paymentServiceModel);

        Assertions.assertEquals(actual.getCvv(), payment.getCvv());
    }

    @Test
    public void findById() throws Exception {
        PaymentServiceModel paymentServiceModel = new PaymentServiceModel();
        paymentServiceModel.setPaymentType("card1");
        paymentServiceModel.setOwner("owner");
        paymentServiceModel.setCardNumber("0000 0000 0000 0000");
        paymentServiceModel.setCvv("cvv");
        paymentServiceModel.setExpirationMonth("January");
        paymentServiceModel.setExpirationYear(12);

        Payment save = paymentService.save(paymentServiceModel);
        String actual = paymentService.findById(save.getId());
        Assertions.assertEquals(actual, payment.getPaymentType());
    }
}
