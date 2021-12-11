package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.entity.*;
import bg.coffeshop.coffeeShop.repository.DeliveryRepository;
import bg.coffeshop.coffeeShop.repository.OrderRepository;
import bg.coffeshop.coffeeShop.repository.PaymentRepository;
import bg.coffeshop.coffeeShop.repository.UserEntityRepository;
import bg.coffeshop.coffeeShop.service.*;
import bg.coffeshop.coffeeShop.util.shoppingCart.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    private DeliveryService deliveryService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private UserService userService;
    private ModelMapper modelMapper;
    @Mock
    private ShoppingCart shoppingCart;
    @Mock
    private DeliveryRepository deliveryRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private UserEntityRepository userEntityRepository;
    @Mock
    private RoleService roleService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        deliveryService = new DeliveryServiceImpl(modelMapper, deliveryRepository);
        paymentService = new PaymentServiceImpl(modelMapper, paymentRepository);
        userService = new UserEntityServiceImpl(userEntityRepository, roleService, passwordEncoder, modelMapper);

        orderService = new OrderServiceImpl(orderRepository, paymentService, deliveryService, userService, modelMapper, shoppingCart);
    }

    @Test
    void save() {
        Order order = new Order();
        order.setClient(new UserEntity());
        order.setDate(LocalDate.now());
        order.setPaymentDetail(new Payment());
        order.setTotalValue(BigDecimal.valueOf(120.00));
        order.setDeliveryDetail(new Delivery());
        order.setProducts(new ArrayList<Product>());
        orderService.save(order);
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).saveAndFlush(orderArgumentCaptor.capture());
        Order order1 = orderArgumentCaptor.getValue();
        assertThat(order1.getTotalValue()).isEqualTo(order.getTotalValue());
    }

    @Test
    void dropTable() {
        orderService.dropTable();
        verify(orderRepository).dropTable();
    }

    @Test
    void findAll() {
        orderService.findAll();
        verify(orderRepository).findAll();
    }
}