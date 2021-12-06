package bg.coffeshop.coffeeShop.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DeliveryBindingModel {

    private String country;
    private String city;
    private String postalCode;
    private String address;
    private String courier;
    private String email;
    private String phone;
    private String person;

    public DeliveryBindingModel() {
    }

    @NotBlank(message = "This field can't be empty")
    @Size(min = 3, message = "The length of this field must be at least of 3 symbols")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Size(min = 3, message = "The length of this field must be at least of 3 symbols")
    @NotBlank(message = "This field can't be empty")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Size(min = 2, message = "The length of this field must be at least of 2 symbols")
    @NotBlank(message = "This field can't be empty")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Size(min = 5, message = "The length of this field must be at least of 5 symbols")
    @NotBlank(message = "This field can't be empty")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotBlank(message = "This field can't be empty")
    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    @NotBlank(message = "This field can't be empty")
    @Pattern(regexp = "^(\\w.+@\\w+)(.\\w+){2,}$", message = "The email must contains: lower and upper case letters, along with @ and dots")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "This field can't be empty")
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s./0-9]*$", message = "The phone number is not in the correct format")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Size(min = 3, message = "The length of this field must be at least of 3 symbols")
    @NotBlank(message = "This field can't be empty")
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
