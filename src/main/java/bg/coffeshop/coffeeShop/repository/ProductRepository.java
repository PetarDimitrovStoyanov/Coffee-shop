package bg.coffeshop.coffeeShop.repository;

import bg.coffeshop.coffeeShop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAll();

    Optional<Product> findByName(String name);

    Optional<Product> findById(Long id);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.id = :id")
    @Transactional
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Product p SET p.name = :name, p.price = :price, p.picture = :picture, p.type = :type WHERE p.id = :id")
    @Transactional
    void update(String name, String picture, BigDecimal price, String type, Long id);
}
