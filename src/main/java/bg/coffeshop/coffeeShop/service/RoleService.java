package bg.coffeshop.coffeeShop.service;

import bg.coffeshop.coffeeShop.constant.RoleEnum;
import bg.coffeshop.coffeeShop.model.entity.Role;

public interface RoleService {
    void initRoles();

    Role findRoleByName(RoleEnum name);
}
