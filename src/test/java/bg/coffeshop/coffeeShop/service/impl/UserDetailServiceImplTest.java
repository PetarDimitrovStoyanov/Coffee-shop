package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.constant.GenderEnum;
import bg.coffeshop.coffeeShop.constant.RoleEnum;
import bg.coffeshop.coffeeShop.model.entity.Role;
import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import bg.coffeshop.coffeeShop.repository.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceImplTest {

    private UserEntity userEntity;
    private ModelMapper modelMapper;
    private Role role;
    @Mock
    private UserEntityRepository userEntityRepository;
    private UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        role = new Role();
        this.modelMapper = new ModelMapper();
        role.setName(RoleEnum.USER);
        userEntity.setUsername("nameIt").setFirstName("Petar").setLastName("stoyanov")
                .setGender(GenderEnum.MALE).setEmail("petar@abv.bg")
                .setAge(18).setRole(role);
        userEntity.setPassword("asd").setPhoneNumber("+359888888888");
        userDetailsService = new UserDetailServiceImpl(userEntityRepository);
    }

    @Test
    public void mapUserError() {
       assertThrows(
                Exception.class,
                () -> userDetailsService.loadUserByUsername("petar")
        );
    }

    @Test
    public void mapUser() {
        UserDetailServiceImpl newUser = new UserDetailServiceImpl(userEntityRepository);
        UserEntity userEntity = new UserEntity();
        Role role = new Role();
        role.setName(RoleEnum.USER);
        userEntity.setRole(role);
        userEntity.setPassword("asdasd");
        userEntity.setEmail("user@emial.com");
        UserDetails userDetails = newUser.mapUser(userEntity);
        assertEquals(userDetails.getAuthorities().toArray()[0].toString(), "ROLE_USER");
    }

    @Test
    public void mapAdmin(){
        UserDetailServiceImpl newUser = new UserDetailServiceImpl(userEntityRepository);
        UserEntity userEntity = new UserEntity();
        Role role = new Role();
        role.setName(RoleEnum.ADMINISTRATOR);
        userEntity.setRole(role);
        userEntity.setPassword("asdasd");
        userEntity.setEmail("administrator@emial.com");
        UserDetails userDetails = newUser.mapAdmin(userEntity);
        assertEquals(userDetails.getAuthorities().toArray()[0].toString(), "ROLE_ADMINISTRATOR");
    }


}