package bg.coffeshop.coffeeShop.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private Set<Product> products;
    private Payment payment;
    private Delivery delivery;
    private LocalDate date;
    private BigDecimal totalValue;
    private UserEntity client;

    public Order() {
    }

    @OneToMany(mappedBy="order", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
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
    public UserEntity getClient() {
        return client;
    }

    public void setClient(UserEntity client) {
        this.client = client;
    }

}
