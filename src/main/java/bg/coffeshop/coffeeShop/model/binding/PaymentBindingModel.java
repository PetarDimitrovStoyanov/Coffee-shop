package bg.coffeshop.coffeeShop.model.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PaymentBindingModel {

    private String paymentType;
    private String cvv;
    private String owner;
    private String cardNumber;
    private String expirationMonth;
    private Integer expirationYear;

    public PaymentBindingModel() {
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @NotBlank(message = "this field cannot be empty!")
    @Size(min = 3, max = 3, message = "This field must contain exactly 3 symbols")
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @NotBlank(message = "this field cannot be empty!")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Pattern(regexp = "^[A-z0-9]{4}[ ][A-z0-9]{4}[ ][A-z0-9]{4}[ ][A-z0-9]{4}$", message = "Not valid card numbers")
    @NotBlank(message = "this field cannot be empty!")
    public String getCardNumber() {
        return cardNumber;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @NotBlank(message = "this field cannot be empty!")
    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Min(value = 1, message = "The year cannot be negative number")
    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }
}
