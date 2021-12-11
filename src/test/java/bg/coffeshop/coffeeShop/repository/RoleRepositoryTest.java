package bg.coffeshop.coffeeShop.repository;

import bg.coffeshop.coffeeShop.constant.GenderEnum;
import bg.coffeshop.coffeeShop.constant.RoleEnum;
import bg.coffeshop.coffeeShop.model.entity.Role;
import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {
    Role role;
    UserEntity userEntity;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserEntityRepository userEntityRepository;

    @BeforeEach
    void setUp() throws Exception {
        role = new Role();
        role.setName(RoleEnum.USER);
        roleRepository.saveAndFlush(role);
        Role returnedRole = roleRepository.findRoleByName(role.getName()).orElseThrow(Exception::new);

        userEntity = new UserEntity();
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

    @Test
    public void findRoleByName() throws Exception {
        List<UserEntity> list = new ArrayList<>();
        list.add(userEntity);
        role.setUserEntity(list);

        Role roleFound = roleRepository.findRoleByName(RoleEnum.USER).orElseThrow(Exception::new);
        assertEquals(roleFound.getName(), RoleEnum.USER);
    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }
}