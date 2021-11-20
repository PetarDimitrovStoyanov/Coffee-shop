package bg.coffeshop.coffeeShop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/double-authentication")
    public String doubleAuthentication() {
        return "double-authentication";
    }

    @GetMapping("/forgotten-password")
    public String password() {
        return "forgotten-password";
    }

    @GetMapping("/user-profile")
    public String profile() {
        return "user-profile";
    }
}
