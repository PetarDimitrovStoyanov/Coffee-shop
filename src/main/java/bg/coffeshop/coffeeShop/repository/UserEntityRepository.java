package bg.coffeshop.coffeeShop.repository;

import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    @Query("Select u from UserEntity u where u.username like %:text%")
    List<UserEntity> findByCriteria(String text);

}
