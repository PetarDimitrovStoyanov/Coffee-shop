package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User getUser(String username);

    List<User> getAllUsers();

    void initFirstUser();

}
