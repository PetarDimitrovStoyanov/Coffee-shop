package bg.coffeshop.coffeeShop.web;

import bg.coffeshop.coffeeShop.model.view.UserViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @GetMapping("/")
    public String homepage(UserViewModel userViewModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("userViewModel", userViewModel);
        redirectAttributes.addFlashAttribute("check", false);
        /* model.addAttribute("check", false);*/
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

    @ModelAttribute
    private UserViewModel loginUserBindingModel() {
        return new UserViewModel();
    }
}
