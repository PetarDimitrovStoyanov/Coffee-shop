package bg.coffeshop.coffeeShop.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    private String type;
    private String picture;
    private Order order;
    private Integer piece;

   public Product() {
   }

   @Column(unique = true, nullable = false)
   public String getName() {
      return name;
   }

   public Product setName(String name) {
      this.name = name;
      return this;
   }

   @Column(nullable = false)
   public BigDecimal getPrice() {
      return price;
   }

   public Product setPrice(BigDecimal price) {
      this.price = price;
      return this;
   }

   @Column
   public String getType() {
      return type;
   }

   public Product setType(String comment) {
      this.type = comment;
      return this;
   }

   @Column(columnDefinition = "LONGBLOB")
   public String getPicture() {
      return picture;
   }

   public Product setPicture(String picture) {
      this.picture = picture;
      return this;
   }

   @ManyToOne()
   public Order getOrder() {
      return order;
   }

   public Product setOrder(Order order) {
      this.order = order;
      return this;
   }

   @Column(nullable = false)
   public Integer getPiece() {
      return piece;
   }

   public void setPiece(Integer piece) {
      this.piece = piece;
   }
}
