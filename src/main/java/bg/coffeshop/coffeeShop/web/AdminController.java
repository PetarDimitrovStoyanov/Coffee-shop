package bg.coffeshop.coffeeShop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/edit-product")
    public String edit() {
        return "edit-product";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/add-product")
    public String addProduct() {
        return "/add-product";
    }

    @GetMapping("/all-users")
    public String allUsers() {
        return "all-users";
    }
}
