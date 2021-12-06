package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.entity.Order;
import bg.coffeshop.coffeeShop.model.view.OrderViewModel;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface OrderService {
    void save(Order order);

    BigDecimal getTotalTurnover();

    void dropTable();

    List<OrderViewModel> findAll();

    Order getOrder(Principal principal) throws Exception;

}
