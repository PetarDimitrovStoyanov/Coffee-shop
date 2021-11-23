package bg.coffeshop.coffeeShop.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payment_details")
public class Payment extends BaseEntity {
    private String paymentType;
    private String provider;
    private String cardType;
    private String cardNumber;
    private List<Order> orders;

    public Payment() {
    }

    @Column(nullable = false, name = "payment_type")
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Column(nullable = false)
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Column(nullable = false, name = "card_type")
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Column(nullable = false, name = "card_number")
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @OneToMany(mappedBy = "paymentDetail")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
