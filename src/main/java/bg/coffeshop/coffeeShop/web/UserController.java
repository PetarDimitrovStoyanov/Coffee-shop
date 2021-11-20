package bg.coffeshop.coffeeShop.web;

import bg.coffeshop.coffeeShop.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/homepage")
    public String homepageTwo() {
        return "homepage";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/user-profile")
    public String profile() {
        return "user-profile";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/moonmilk")
    public String moonmilk() {
        return "moonmilk";
    }

    @GetMapping("/location-one")
    public String locationOne() {
        return "location-one";
    }

    @GetMapping("/location-two")
    public String locationTwo() {
        return "location-two";
    }

    @GetMapping("/location-three")
    public String locationThree() {
        return "location-three";
    }


}
