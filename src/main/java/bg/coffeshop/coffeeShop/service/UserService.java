package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.model.service.UserRegistrationServiceModel;
import bg.coffeshop.coffeeShop.model.binding.UserRegisterBindingModel;
import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import bg.coffeshop.coffeeShop.model.service.UserServiceModel;
import bg.coffeshop.coffeeShop.model.view.UserViewModel;

import java.util.List;

public interface UserService {

    UserEntity saveUser(UserEntity userEntity);

    UserEntity getUser(String username) throws Exception;

    List<UserEntity> getAllUsers();

    void initFirstUser() throws Exception;

    void register(UserRegistrationServiceModel userEntity) throws Exception;

    boolean isUserExists(UserRegisterBindingModel userRegisterBindingModel);

    UserServiceModel findByName(String name) throws Exception;

    List<UserViewModel> findAll();

    void deleteUser(Long id);

    void changeCurrentUserRole(Long id) throws Exception;

    List<UserViewModel> findByNameCriteria(String name);

    String findById(Long id) throws Exception;

    UserServiceModel findByIdExport(Long id) throws Exception;

    UserRegistrationServiceModel getUserRegistrationServiceModel(UserRegisterBindingModel userRegisterBindingModel)
            throws Exception;
}
