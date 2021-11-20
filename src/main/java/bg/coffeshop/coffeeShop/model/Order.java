package bg.coffeshop.coffeeShop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private List<Product> products;
    private Payment payment;
    private Delivery delivery;
    private LocalDate date;
    private BigDecimal totalValue;
    private User client;
    private Integer discount;

    public Order() {
    }

    @ManyToMany()
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @ManyToOne
    public Payment getPaymentDetail() {
        return payment;
    }

    public void setPaymentDetail(Payment payment) {
        this.payment = payment;
    }

    @ManyToOne
    public Delivery getDeliveryDetail() {
        return delivery;
    }

    public void setDeliveryDetail(Delivery delivery) {
        this.delivery = delivery;
    }

    @Column(nullable = false)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name = "total_value", nullable = false)
    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    @ManyToOne
    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Column
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
