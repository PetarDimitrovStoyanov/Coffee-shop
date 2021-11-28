package bg.coffeshop.coffeeShop.model.service;

import bg.coffeshop.coffeeShop.model.entity.Order;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class ProductServiceModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private Integer piece;
    private String type;
    private String picture;
    private List<Order> orders;

    public ProductServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "The name is missing!")
    @Size(min = 3, message = "The name should be at least 3 characters long!")
    public String getName() {
        return name;
    }

    public ProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull(message = "The price is missing!")
    @Min(value = 0, message = "The price should be a positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public ProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getType() {
        return type;
    }

    public ProductServiceModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public ProductServiceModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public ProductServiceModel setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
