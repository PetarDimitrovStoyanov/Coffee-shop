package bg.coffeshop.coffeeShop.repository;

import bg.coffeshop.coffeeShop.constant.GenderEnum;
import bg.coffeshop.coffeeShop.constant.RoleEnum;
import bg.coffeshop.coffeeShop.model.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {
    Order order;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    UserEntityRepository userEntityRepository;
    @Autowired
    RoleRepository roleRepository;

    @BeforeEach
    void setUp() throws Exception {
        order = new Order();

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Product product = new Product();
            product.setName("Name" + i);
            product.setPiece(0);
            product.setPrice(BigDecimal.valueOf(i));
            product.setPicture("some random picture");
            product.setType("Something");
            products.add(product);
        }

        order.setProducts(products);
        order.setTotalValue(BigDecimal.valueOf(120.00));
        order.setDate(LocalDate.now());

        Role role = new Role();
        role.setName(RoleEnum.USER);
        roleRepository.saveAndFlush(role);
        Role returnedRole = roleRepository.findRoleByName(role.getName()).orElseThrow(Exception::new);

        UserEntity userEntity = new UserEntity();
        userEntity
                .setEmail("some@email.bg")
                .setPassword("asd")
                .setAge(19)
                .setGender(GenderEnum.MALE)
                .setFirstName("Petar")
                .setLastName("Stoyanov")
                .setUsername("petarstoyanov")
                .setRole(returnedRole);
        userEntityRepository.saveAndFlush(userEntity);
        UserEntity returnedUserEntity = userEntityRepository.findByUsername(userEntity.getUsername()).orElseThrow(Exception::new);

        order.setClient(returnedUserEntity);

        Delivery delivery = new Delivery();
        delivery.setPhone("+359888888888");
        delivery.setEmail("some@email.bg");
        delivery.setPerson("someone");
        delivery.setCountry("USA");
        delivery.setCity("Florida");
        delivery.setAddress("blvd Sunset 31");
        delivery.setPostalCode("9999");
        delivery.setCourier("DHL");
        deliveryRepository.saveAndFlush(delivery);
        Delivery returnedDelivery = new ArrayList<>(deliveryRepository.findAll()).get(0);

        order.setDeliveryDetail(returnedDelivery);

        Payment payment = new Payment();
        payment.setPaymentType("card");
        payment.setOwner("someoner");
        payment.setCvv("cvv");
        payment.setExpirationMonth("January");
        payment.setExpirationYear(26);
        payment.setCardNumber("0000 0000 0000 0000");

        paymentRepository.saveAndFlush(payment);
        Payment returnedPayment = new ArrayList<>(paymentRepository.findAll()).get(0);
        System.out.println(returnedPayment);

        order.setPaymentDetail(returnedPayment);
    }

    @Test
    public void saveAndFlush() {
        orderRepository.saveAndFlush(order);
        Assertions.assertEquals(orderRepository.count(), 1);
    }

    @Test
    public void dropTable() {
        orderRepository.saveAndFlush(order);
        Assertions.assertEquals(orderRepository.count(), 1);
        orderRepository.dropTable();
        Assertions.assertEquals(orderRepository.count(), 0);
    }

    @Test
    public void getTotalTurnover() {
        orderRepository.saveAndFlush(order);
        assertEquals(orderRepository.getOrdersTurnover().toString(), BigDecimal.valueOf(120.00).toString() + "0");
    }


}