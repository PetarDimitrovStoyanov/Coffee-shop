package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.constant.GenderEnum;
import bg.coffeshop.coffeeShop.constant.RoleEnum;
import bg.coffeshop.coffeeShop.model.service.UserRegistrationServiceModel;
import bg.coffeshop.coffeeShop.model.service.UserServiceModel;
import bg.coffeshop.coffeeShop.model.binding.UserRegisterBindingModel;
import bg.coffeshop.coffeeShop.model.entity.Role;
import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import bg.coffeshop.coffeeShop.model.view.UserViewModel;
import bg.coffeshop.coffeeShop.repository.UserEntityRepository;
import bg.coffeshop.coffeeShop.service.RoleService;
import bg.coffeshop.coffeeShop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserEntityServiceImpl implements UserService {
    private final UserEntityRepository userEntityRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserEntityServiceImpl(UserEntityRepository userEntityRepository, RoleService roleService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userEntityRepository = userEntityRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.saveAndFlush(userEntity);
    }

    @Override
    public UserEntity getUser(String username) throws Exception {
        return userEntityRepository.findByUsername(username).orElseThrow(Exception::new);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userEntityRepository.findAll();
    }

    @Override
    @Transactional
    @Modifying
    public void initFirstUser() throws Exception {
        if (this.userEntityRepository.count() == 0) {
            UserDetailServiceImpl userDetailServiceImpl = new UserDetailServiceImpl(this.userEntityRepository);

            UserEntity userEntity = new UserEntity();
            Role role = this.roleService.findRoleByName(RoleEnum.ADMINISTRATOR);
            userEntity
                    .setUsername("admin")
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setGender(GenderEnum.MALE)
                    .setEmail("admin@abv.bg")
                    .setPassword(this.passwordEncoder.encode("asd"))
                    .setPhoneNumber("+359 888 888 888")
                    .setAge(18)
                    .setRole(role);
            this.userEntityRepository.saveAndFlush(userEntity);
            userDetailServiceImpl.mapAdmin(userEntity);

            UserEntity userEntity2 = new UserEntity();
            Role role2 = this.roleService.findRoleByName(RoleEnum.USER);
            userEntity2
                    .setUsername("usercho")
                    .setFirstName("User")
                    .setLastName("Userov")
                    .setGender(GenderEnum.MALE)
                    .setEmail("user@abv.bg")
                    .setPassword(this.passwordEncoder.encode("asd"))
                    .setPhoneNumber("+359 888 888 989")
                    .setAge(18)
                    .setRole(role2);
            this.userEntityRepository.saveAndFlush(userEntity2);
        }
    }

    @Transactional
    @Modifying
    public UserEntity register(UserRegistrationServiceModel userEntity) throws Exception {
        UserEntity user = this.modelMapper.map(userEntity, UserEntity.class);
        user.setRole(this.roleService.findRoleByName(RoleEnum.USER));
        return this.userEntityRepository.saveAndFlush(user);
    }

    @Override
    public boolean isUserExists(UserRegisterBindingModel userRegisterBindingModel) {
        Optional<UserEntity> userEntity = this.userEntityRepository.findByEmail(userRegisterBindingModel.getEmail());
        return userEntity.isPresent();
    }

    @Override
    public UserServiceModel findByName(String name) throws Exception {
        UserEntity userEntity = this.userEntityRepository.findByEmail(name).orElseThrow(Exception::new);
        return this.modelMapper.map(userEntity, bg.coffeshop.coffeeShop.model.service.UserServiceModel.class);
    }

    @Override
    public List<UserViewModel> findAll() {
        List<UserEntity> users = this.userEntityRepository.findAll();
        List<UserViewModel> userViewModels = new ArrayList<>();

        for (UserEntity user : users) {
            UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
            userViewModels.add(userViewModel);
        }

        return userViewModels;
    }

    @Override
    @Modifying
    @Transactional
    public void deleteUser(Long id) {
        this.userEntityRepository.deleteById(id);
    }

    @Override
    @Transactional
    @Modifying
    public void changeCurrentUserRole(Long id) throws Exception {
        UserEntity user = this.userEntityRepository.findById(id).orElseThrow(Exception::new);
        UserDetailServiceImpl userDetailServiceImpl = new UserDetailServiceImpl(this.userEntityRepository);

        if (user.getRole().getName().name().equalsIgnoreCase("ADMINISTRATOR")) {
            user.setRole(this.roleService.findRoleByName(RoleEnum.USER));
            userDetailServiceImpl.mapUser(this.modelMapper.map(user, UserEntity.class));
        } else {
            user.setRole(this.roleService.findRoleByName(RoleEnum.ADMINISTRATOR));
            userDetailServiceImpl.mapAdmin(this.modelMapper.map(user, UserEntity.class));
        }
    }

    @Override
    public List<UserViewModel> findByNameCriteria(String name) {
        List<UserEntity> users = this.userEntityRepository.findByCriteria(name);
        if (users.isEmpty()) {
            return new ArrayList<>();
        } else {
            return users.stream().map(u -> this.modelMapper.map(u, UserViewModel.class)).collect(Collectors.toList());
        }
    }

    @Override
    public String findById(Long id) throws Exception {
        UserEntity userEntity = this.userEntityRepository.findById(id).orElseThrow(Exception::new);
        return userEntity.getFirstName() + " " + userEntity.getLastName();
    }

    @Override
    public UserServiceModel findByIdExport(Long id) throws Exception {
        UserEntity user = this.userEntityRepository.findById(id).orElseThrow(Exception::new);
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserRegistrationServiceModel getUserRegistrationServiceModel(UserRegisterBindingModel userRegisterBindingModel)
            throws Exception {
        UserRegistrationServiceModel userRegistrationServiceModel = this.modelMapper
                .map(userRegisterBindingModel, UserRegistrationServiceModel.class);

        Role role = this.roleService.findRoleByName(RoleEnum.USER);

        userRegistrationServiceModel.setRole(role);
        userRegistrationServiceModel.setOrders(new ArrayList<>());
        userRegistrationServiceModel.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));
        return userRegistrationServiceModel;
    }

}
