package bg.coffeshop.coffeeShop.model.view;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderViewModel {
    private Long id;
    private String payment;
    private String delivery;
    private LocalDate date;
    private BigDecimal totalValue;
    private String client;

    public OrderViewModel() {
    }

    public Long getId() {
        return id;
    }

    public OrderViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPayment() {
        return payment;
    }

    public OrderViewModel setPayment(String payment) {
        this.payment = payment;
        return this;
    }

    public String getDelivery() {
        return delivery;
    }

    public OrderViewModel setDelivery(String delivery) {
        this.delivery = delivery;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OrderViewModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public OrderViewModel setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public String getClient() {
        return client;
    }

    public OrderViewModel setClient(String client) {
        this.client = client;
        return this;
    }
}
