package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.constant.RoleEnum;
import bg.coffeshop.coffeeShop.model.entity.Role;
import bg.coffeshop.coffeeShop.repository.RoleRepository;
import bg.coffeshop.coffeeShop.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if (this.roleRepository.count() == 0) {
            Role roleUser = new Role();
            roleUser.setName(RoleEnum.USER);

            Role roleAdmin = new Role();
            roleAdmin.setName(RoleEnum.ADMINISTRATOR);

            this.roleRepository.saveAndFlush(roleUser);
            this.roleRepository.saveAndFlush(roleAdmin);
        }
    }

    @Override
    public Role findRoleByName(RoleEnum name) throws Exception {
        return this.roleRepository.findRoleByName(name).orElseThrow(Exception::new);
    }
}
