package bg.coffeshop.coffeeShop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    private String type;
    private String picture;
    private List<Order> orders;

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

   @ManyToMany(mappedBy = "products")
   public List<Order> getOrders() {
      return orders;
   }

   public Product setOrders(List<Order> orders) {
      this.orders = orders;
      return this;
   }
}
