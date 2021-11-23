package bg.coffeshop.coffeeShop.repository;

import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}
