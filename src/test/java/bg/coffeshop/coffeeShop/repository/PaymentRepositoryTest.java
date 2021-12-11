package bg.coffeshop.coffeeShop.repository;

import bg.coffeshop.coffeeShop.model.entity.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void test() {
        Payment payment = new Payment();
        payment.setPaymentType("card");
        payment.setOwner("owner");
        payment.setCardNumber("0000 0000 0000 0000");
        payment.setCvv("cvv");
        payment.setExpirationMonth("January");
        payment.setExpirationYear(12);

        paymentRepository.saveAndFlush(payment);
        Assertions.assertEquals(paymentRepository.count(), 1);
    }
}
