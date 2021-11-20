package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.constant.GenderEnum;
import bg.coffeshop.coffeeShop.constant.RoleEnum;
import bg.coffeshop.coffeeShop.model.User;
import bg.coffeshop.coffeeShop.repository.UserRepository;
import bg.coffeshop.coffeeShop.service.UserService;
/*import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    /*private final PasswordEncoder passwordEncoder;*/

    public UserServiceImpl(UserRepository userRepository /*PasswordEncoder passwordEncoder*/) {
        this.userRepository = userRepository;
        /* this.passwordEncoder = passwordEncoder;*/
    }

    @Override
    public User saveUser(User user) {
        /*user.setPassword(passwordEncoder.encode(user.getPassword()));*/
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void initFirstUser() {
        if (this.userRepository.count() == 0) {
            User user = new User();
            Set<RoleEnum> roles = new HashSet<>();
            roles.add(RoleEnum.ADMINISTRATOR);

            user
                    .setUsername("admin")
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setGender(GenderEnum.MALE)
                    .setEmail("admin@abv.bg")
                    .setPassword("asd")
                    .setPhoneNumber("+359 888 888 888")
                    .setAge(18)
                    .setRoles(roles);

            this.userRepository.saveAndFlush(user);
        }
    }

}
