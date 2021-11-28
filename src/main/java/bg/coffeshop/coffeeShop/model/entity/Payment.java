package bg.coffeshop.coffeeShop.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payment_details")
public class Payment extends BaseEntity {
    private String paymentType;
    private String owner;
    private String cvv;
    private String expirationMonth;
    private Integer expirationYear;
    private String cardNumber;
    private List<Order> orders;

    public Payment() {
    }

    @Column(name = "payment_type")
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Column(nullable = false)
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Column(nullable = false)
    public String getCvv() {
        return cvv;
    }


    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Column(nullable = false, name = "expiration_month")
    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Column(nullable = false, name = "expiration_year")
    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
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
