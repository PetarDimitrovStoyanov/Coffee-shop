package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity saveUser(UserEntity userEntity);

    UserEntity getUser(String username);

    List<UserEntity> getAllUsers();

    void initFirstUser();

}
