package bg.coffeshop.coffeeShop.util.shoppingCart;

import bg.coffeshop.coffeeShop.model.entity.Delivery;
import bg.coffeshop.coffeeShop.model.entity.Payment;
import org.springframework.beans.factory.DisposableBean;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements DisposableBean {
    private List<ShoppingCartEntity> items;
    private Delivery delivery;
    private Payment payment;

    public ShoppingCart(List<ShoppingCartEntity> list) {
        items = list;
    }

    public List<ShoppingCartEntity> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartEntity> items) {
        this.items = items;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public void destroy() throws Exception {
        this.setPayment(null);
        this.setDelivery(null);
        this.setItems(new ArrayList<>());
    }

}
