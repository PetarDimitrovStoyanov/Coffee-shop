package bg.coffeshop.coffeeShop.util.shoppingCart;

import bg.coffeshop.coffeeShop.model.entity.Product;

import java.math.BigDecimal;

public class ShoppingCartEntity {
    private Product product;
    private Integer piece;
    private BigDecimal price;
    private BigDecimal total;

    public ShoppingCartEntity() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return new BigDecimal(piece * Double.parseDouble(price.toString()));
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
