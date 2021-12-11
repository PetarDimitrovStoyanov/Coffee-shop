package bg.coffeshop.coffeeShop.repository;

import bg.coffeshop.coffeeShop.constant.GenderEnum;
import bg.coffeshop.coffeeShop.constant.RoleEnum;
import bg.coffeshop.coffeeShop.model.entity.Role;
import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserEntityRepositoryTest {

    @Autowired
    UserEntityRepository userEntityRepository;
    @Autowired
    RoleRepository roleRepository;

    @BeforeEach
    void setUp() throws Exception {
        Role role = new Role();
        role.setName(RoleEnum.USER);
        roleRepository.saveAndFlush(role);
        Role returnedRole = roleRepository.findRoleByName(role.getName()).orElseThrow(Exception::new);

        UserEntity userEntity = new UserEntity();
        userEntity
                .setEmail("some@email.bg")
                .setPassword("asd")
                .setAge(19)
                .setGender(GenderEnum.MALE)
                .setFirstName("Petar")
                .setLastName("Stoyanov")
                .setUsername("petarstoyanov")
                .setRole(returnedRole);
        userEntityRepository.saveAndFlush(userEntity);
    }

    @AfterEach
    void tearDown() {
        userEntityRepository.deleteAll();
        roleRepository.deleteAll();
    }
}