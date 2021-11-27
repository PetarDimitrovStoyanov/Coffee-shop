package bg.coffeshop.coffeeShop.web;

import bg.coffeshop.coffeeShop.model.Service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.binding.ProductShoppingCartBindingModel;
import bg.coffeshop.coffeeShop.util.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class UserController {

    private final ShoppingCart shoppingCart;

    public UserController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
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

    @GetMapping("/shopping-cart")
    public String cart() {
        return "shopping-cart";
    }

    @GetMapping("/user-profile")
    public String profile() {
        return "user-profile";
    }

    @PostMapping("/products")
    public String addToShoppingCart(@RequestParam String productName, @RequestParam String type, @RequestParam Integer piece, @RequestParam BigDecimal price) {
        System.out.println(productName);
        System.out.println(price.toString());
        System.out.println("test");
        return "/";
    }

    @ModelAttribute
    private ProductShoppingCartBindingModel productShoppingCart() {
        return new ProductShoppingCartBindingModel();
    }

}
