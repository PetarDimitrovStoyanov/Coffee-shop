package bg.coffeshop.coffeeShop.init;

import bg.coffeshop.coffeeShop.service.ProductService;
import bg.coffeshop.coffeeShop.service.RoleService;
import bg.coffeshop.coffeeShop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final ProductService productService;

    public DataBaseInit(UserService userService, RoleService roleService, ProductService productService) {
        this.userService = userService;
        this.roleService = roleService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
        this.userService.initFirstUser();
    }
}
