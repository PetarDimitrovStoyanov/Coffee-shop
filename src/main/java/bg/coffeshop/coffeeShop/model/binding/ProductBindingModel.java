package bg.coffeshop.coffeeShop.model.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductBindingModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String type;
    private String picture;

    public ProductBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "The price is missing!")
    @Size(min = 3, message = "The name should be at least 3 characters long!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(value = 0, message = "The price should be positive number!")
    @NotNull(message = "The price is missing!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotBlank(message = "The return type is missing!")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
