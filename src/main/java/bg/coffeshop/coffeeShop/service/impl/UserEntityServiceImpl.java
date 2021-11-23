package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.constant.GenderEnum;
import bg.coffeshop.coffeeShop.constant.RoleEnum;
import bg.coffeshop.coffeeShop.model.entity.Role;
import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import bg.coffeshop.coffeeShop.repository.UserEntityRepository;
import bg.coffeshop.coffeeShop.service.RoleService;
import bg.coffeshop.coffeeShop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserEntityServiceImpl implements UserService {
    private final UserEntityRepository userEntityRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserEntityServiceImpl(UserEntityRepository userEntityRepository, RoleService roleService , PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.saveAndFlush(userEntity);
    }

    @Override
    public UserEntity getUser(String username) {
        return userEntityRepository.findByUsername(username);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userEntityRepository.findAll();
    }

    @Override
    public void initFirstUser() {
        if (this.userEntityRepository.count() == 0) {
            UserEntity userEntity = new UserEntity();

            List<Role> roles = new ArrayList<>();
            Role role = this.roleService.findRoleByName(RoleEnum.ADMINISTRATOR);
            roles.add(role);

            userEntity
                    .setUsername("admin")
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setGender(GenderEnum.MALE)
                    .setEmail("admin@abv.bg")
                    .setPassword(this.passwordEncoder.encode("asd"))
                    /*.setPassword("asd")*/
                    .setPhoneNumber("+359 888 888 888")
                    .setAge(18)
                    .setRoles(roles);

            this.userEntityRepository.saveAndFlush(userEntity);


            UserEntity userEntity2 = new UserEntity();

            List<Role> roles2 = new ArrayList<>();
            Role role2 = this.roleService.findRoleByName(RoleEnum.USER);
            roles2.add(role2);

            userEntity2
                    .setUsername("usercho")
                    .setFirstName("User")
                    .setLastName("Userov")
                    .setGender(GenderEnum.MALE)
                    .setEmail("user@abv.bg")
                    .setPassword(this.passwordEncoder.encode("asd"))
                    /*.setPassword("asd")*/
                    .setPhoneNumber("+359 888 888 989")
                    .setAge(18)
                    .setRoles(roles2);

            this.userEntityRepository.saveAndFlush(userEntity2);
        }
    }

}
