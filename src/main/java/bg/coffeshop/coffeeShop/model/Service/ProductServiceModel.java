package bg.coffeshop.coffeeShop.model.Service;

import bg.coffeshop.coffeeShop.model.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceModel {

    private String name;
    private BigDecimal price;
    private String type;
    private Boolean status;
    private String picture;
    private List<Order> orders;

    public ProductServiceModel() {
    }

    public String getName() {
        return name;
    }

    public ProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

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

    public Boolean getStatus() {
        return status;
    }

    public ProductServiceModel  setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public ProductServiceModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public ProductServiceModel  setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
