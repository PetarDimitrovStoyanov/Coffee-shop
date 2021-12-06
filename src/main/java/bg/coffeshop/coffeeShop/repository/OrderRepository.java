package bg.coffeshop.coffeeShop.repository;

import bg.coffeshop.coffeeShop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT SUM(o.totalValue) FROM Order o")
    BigDecimal getOrdersTurnover();

    @Transactional
    @Modifying
    @Query("DELETE FROM Order")
    void dropTable();

    @Transactional
    @Query(value = "SELECT o FROM orders ORDER BY o.id DESC LIMIT 1", nativeQuery=true)
    Optional<Order> getLastOrder();
}
