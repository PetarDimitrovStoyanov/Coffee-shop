package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.constant.GenderEnum;
import bg.coffeshop.coffeeShop.constant.RoleEnum;
import bg.coffeshop.coffeeShop.model.binding.UserRegisterBindingModel;
import bg.coffeshop.coffeeShop.model.entity.Role;
import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import bg.coffeshop.coffeeShop.model.service.UserServiceModel;
import bg.coffeshop.coffeeShop.model.view.UserViewModel;
import bg.coffeshop.coffeeShop.repository.RoleRepository;
import bg.coffeshop.coffeeShop.repository.UserEntityRepository;
import bg.coffeshop.coffeeShop.service.RoleService;
import bg.coffeshop.coffeeShop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserEntityServiceImplTest {
    private UserEntity userEntity;
    private Role userRole;
    private UserService serviceToTest;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Mock
    private UserEntityRepository mockUserEntityRepository;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    private void init() {
        userEntity = new UserEntity();
        userRole = new Role();
        this.modelMapper = new ModelMapper();
        userRole.setName(RoleEnum.USER);
        serviceToTest = new UserEntityServiceImpl(mockUserEntityRepository, roleService, passwordEncoder, modelMapper);
        userEntity.setUsername("nameIt").setFirstName("Petar").setLastName("stoyanov")
                .setGender(GenderEnum.MALE).setEmail("petar@abv.bg")
                .setAge(18).setRole(userRole);
        userEntity.setPassword("asd").setPhoneNumber("+359888888888");
    }

    @Test
    public void userNotFound() {
        assertThrows(
                Exception.class,
                () -> serviceToTest.findByName("invalid_name")
        );
    }

    @Test
    public void userFound() throws Exception {
        when(mockUserEntityRepository.findByUsername(userEntity.getUsername()))
                .thenReturn(Optional.of(userEntity));

        var actual = serviceToTest.getUser(userEntity.getUsername());

        Assertions.assertEquals(actual.getUsername(), userEntity.getUsername());
    }

    @Test
    public void findById() throws Exception {
        when(mockUserEntityRepository.findById(userEntity.getId()))
                .thenReturn(Optional.of(userEntity));

        var actual = serviceToTest.findById(userEntity.getId());

        Assertions.assertEquals(actual, userEntity.getFirstName() + " " + userEntity.getLastName());
    }

    @Test
    public void findByIdExport() throws Exception {
        when(mockUserEntityRepository.findById(userEntity.getId()))
                .thenReturn(Optional.of(userEntity));

        var actual = serviceToTest.findByIdExport(userEntity.getId());
        UserServiceModel map = this.modelMapper.map(actual, UserServiceModel.class);

        Assertions.assertEquals(map.getEmail(), userEntity.getEmail());
    }

    @Test
    public void allUsers() {
        when(mockUserEntityRepository.findAll())
                .thenReturn(List.of(userEntity));
        var actual = serviceToTest.findAll()
                .stream()
                .map(u -> modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList());

        Assertions.assertEquals(actual.size(), 1);
    }

    @Test
    public void saveUser() throws Exception {
        UserEntity userEntity2 = new UserEntity();
        userRole = new Role();
        this.modelMapper = new ModelMapper();
        this.passwordEncoder = new BCryptPasswordEncoder();
        userRole.setName(RoleEnum.ADMINISTRATOR);

        userEntity2
                .setUsername("nameIt2")
                .setFirstName("Petar2")
                .setLastName("stoyanov2")
                .setGender(GenderEnum.MALE)
                .setEmail("petar2@abv.bg")
                .setAge(22)
                .setRole(userRole);
        userEntity2.setPassword("asd").setPhoneNumber("+359888888828");

        serviceToTest = new UserEntityServiceImpl(mockUserEntityRepository, roleService, passwordEncoder, modelMapper);


        when(mockUserEntityRepository.saveAndFlush(userEntity2))
                .thenReturn(userEntity2);

        when(mockUserEntityRepository.findByUsername(userEntity2.getUsername()))
                .thenReturn(Optional.of(userEntity2));

        UserEntity actual = serviceToTest.saveUser(userEntity2);
        Assertions.assertEquals(actual, serviceToTest.getUser(userEntity2.getUsername()));

    }

    @Test
    public void isUserExists() {
        this.modelMapper = new ModelMapper();
        this.passwordEncoder = new BCryptPasswordEncoder();
        serviceToTest = new UserEntityServiceImpl(mockUserEntityRepository, roleService, passwordEncoder, modelMapper);

        serviceToTest.saveUser(userEntity);
        when(mockUserEntityRepository.findByEmail(userEntity.getEmail()))
                .thenReturn(Optional.of(userEntity));

        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setEmail(userEntity.getEmail());
        Assertions.assertTrue(serviceToTest.isUserExists(userRegisterBindingModel));
    }

    @Test
    public void deleteUser() {
        mockUserEntityRepository.save(userEntity);
        mockUserEntityRepository.deleteById(userEntity.getId());
        serviceToTest.deleteUser(userEntity.getId());

        var actual2 = serviceToTest.findAll()
                .stream()
                .map(u -> modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList());

        Assertions.assertEquals(actual2.size(), 0);
    }

    @Test
    public void getAllUsers() {
        serviceToTest.getAllUsers();
        verify(mockUserEntityRepository).findAll();
    }

    @Test
    public void findByNameCriteria() {
        serviceToTest.findByNameCriteria("some");
        verify(mockUserEntityRepository).findByCriteria("some");
    }


}
