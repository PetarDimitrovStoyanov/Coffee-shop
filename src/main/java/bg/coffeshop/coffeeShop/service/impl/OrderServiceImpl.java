package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.constant.Constant;
import bg.coffeshop.coffeeShop.model.entity.Order;
import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import bg.coffeshop.coffeeShop.model.service.UserServiceModel;
import bg.coffeshop.coffeeShop.model.view.OrderViewModel;
import bg.coffeshop.coffeeShop.repository.OrderRepository;
import bg.coffeshop.coffeeShop.service.DeliveryService;
import bg.coffeshop.coffeeShop.service.OrderService;
import bg.coffeshop.coffeeShop.service.PaymentService;
import bg.coffeshop.coffeeShop.service.UserService;
import bg.coffeshop.coffeeShop.util.shoppingCart.ShoppingCart;
import bg.coffeshop.coffeeShop.util.shoppingCart.ShoppingCartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final DeliveryService deliveryService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ShoppingCart shoppingCart;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentService paymentService, DeliveryService deliveryService, UserService userService, ModelMapper modelMapper, ShoppingCart shoppingCart) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
        this.deliveryService = deliveryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.shoppingCart = shoppingCart;
    }

    @Override
    @Transactional
    @Modifying
    public void save(Order order) {
        Order orderDone = this.orderRepository.saveAndFlush(order);
        result(orderDone);
    }

    public void result(Order order) {
        FileWriter myWriter;
        try {
            myWriter = new FileWriter(Constant.PATH_FILE);

            StringBuilder products = new StringBuilder(System.lineSeparator());
            for (Product product : order.getProducts()) {
                products.append("^   * Product name: ").append(product.getName())
                        .append(System.lineSeparator())
                        .append("^   * Product type: ").append(product.getType())
                        .append(System.lineSeparator())
                        .append("^   * Product price: ").append(product.getPrice()).append("$")
                        .append(System.lineSeparator())
                        .append("^   * Product pieces: ").append(product.getPiece())
                        .append(System.lineSeparator())
                        .append("^   ___________________")
                        .append(System.lineSeparator());
            }

            StringBuilder payment = new StringBuilder(System.lineSeparator());
            payment.append("^   * Payment type: ").append(order.getPaymentDetail().getPaymentType())
                    .append(System.lineSeparator());

            StringBuilder builder = new StringBuilder();
            builder.append("Order id: ").append(order.getId())
                    .append(System.lineSeparator())
                    .append("^Total value: ").append(order.getTotalValue().doubleValue()).append("$")
                    .append(System.lineSeparator())
                    .append("^<--------------------------------------------------------->")
                    .append(System.lineSeparator())
                    .append("^Client: ")
                    .append(order.getClient().getFirstName()).append(" ")
                    .append(order.getClient().getLastName())
                    .append(System.lineSeparator()).append("^Client phone number: ").append(order.getClient().getPhoneNumber())
                    .append(System.lineSeparator()).append("^Client email: ").append(order.getClient().getEmail())
                    .append(System.lineSeparator()).append("^Ordered products: ").append(products)
                    .append("^<-------------------------------------------------------->")
                    .append(System.lineSeparator()).append("^Payment details: ").append(payment)
                    .append("^<-------------------------------------------------------->")
                    .append(System.lineSeparator())
                    .append("^Delivery details:").append(System.lineSeparator())
                    .append("^   * Delivery address: ").append(order.getDeliveryDetail().getAddress())
                    .append(System.lineSeparator())
                    .append("^   * City: ").append(order.getDeliveryDetail().getCity()).append(System.lineSeparator())
                    .append("^   * Postal code: ").append(order.getDeliveryDetail().getPostalCode())
                    .append(System.lineSeparator())
                    .append("^   * Country: ").append(order.getDeliveryDetail().getCountry())
                    .append(System.lineSeparator())
                    .append("^   * Receiver name: ").append(order.getDeliveryDetail().getPerson())
                    .append(System.lineSeparator())
                    .append("^   * Receiver phone: ").append(order.getDeliveryDetail().getPhone())
                    .append(System.lineSeparator())
                    .append("^   * Receiver email: ").append(order.getDeliveryDetail().getEmail())
                    .append(System.lineSeparator())
                    .append("^   * Courier: ").append(order.getDeliveryDetail().getCourier())
                    .append(System.lineSeparator());

            String fileContent = builder.toString();
            myWriter.write(fileContent);
            myWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BigDecimal getTotalTurnover() {
        return this.orderRepository.getOrdersTurnover();
    }

    @Override
    public void dropTable() {
        this.orderRepository.dropTable();
    }

    @Override
    public List<OrderViewModel> findAll() {
        List<Order> orders = this.orderRepository.findAll();
        return orders.stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public Order getOrder(Principal principal) throws Exception {
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setPaymentDetail(shoppingCart.getPayment());
        order.setDeliveryDetail(shoppingCart.getDelivery());
        double totalAmount = 0.00;
        List<Product> productList = new ArrayList<>();
        for (ShoppingCartEntity shoppingCartEntity : shoppingCart.getItems()) {
            Product product = this.modelMapper.map(shoppingCartEntity.getProduct(), Product.class);
            productList.add(product);
            product.setPiece(shoppingCartEntity.getPiece());
            double total = Double.parseDouble(shoppingCartEntity.getTotal().toString());
            totalAmount += total;
        }
        order.setProducts(productList);
        UserServiceModel user = this.userService.findByName(principal.getName());
        order.setClient(this.modelMapper.map(user, UserEntity.class));
        order.setTotalValue(new BigDecimal(totalAmount));
        return order;
    }

    private OrderViewModel map(Order order) {
        try {
            return new OrderViewModel()
                    .setTotalValue(order.getTotalValue())
                    .setDelivery(this.deliveryService.findById(order.getDeliveryDetail().getId()))
                    .setPayment(this.paymentService.findById(order.getPaymentDetail().getId()))
                    .setClient(this.userService.findById(order.getClient().getId()))
                    .setId(order.getId())
                    .setDate(order.getDate());
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
