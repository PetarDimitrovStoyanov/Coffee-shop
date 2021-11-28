package bg.coffeshop.coffeeShop.web;

import bg.coffeshop.coffeeShop.model.Service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.binding.ProductBasketBindingModel;
import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.model.view.ProductViewModel;
import bg.coffeshop.coffeeShop.service.ProductService;
import bg.coffeshop.coffeeShop.util.ShoppingCart;
import bg.coffeshop.coffeeShop.util.ShoppingCartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final ShoppingCart shoppingCart;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public UserController(ShoppingCart shoppingCart, ProductService productService, ModelMapper modelMapper) {
        this.shoppingCart = shoppingCart;
        this.productService = productService;
        this.modelMapper = modelMapper;
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
    public String products(Model model) {
        List<ProductViewModel> productViewModels = this.productService.findAll();
        model.addAttribute("products", productViewModels);
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

    @GetMapping("/order-it/{id}")
    public String orderIt(@PathVariable Long id, Model model) {
        ProductServiceModel productServiceModel = this.modelMapper.map(this.productService.findById(id), ProductServiceModel.class);
        model.addAttribute("productServiceModel", productServiceModel);
        return "order-it";
    }

    @PostMapping("/order-it/{id}")
    public String addToBasket(ProductServiceModel productServiceModel, @PathVariable Long id) {
        Product product = this.modelMapper.map(this.productService.findById(id), Product.class);

        ShoppingCartEntity entity = new ShoppingCartEntity();
        entity.setPiece(productServiceModel.getPiece());
        entity.setPrice(product.getPrice());
        entity.setProduct(product);

        this.shoppingCart.getItems().add(entity);
        return "redirect:/products";
    }

    @ModelAttribute
    private ProductBasketBindingModel productBasketBindingModel() {
        return new ProductBasketBindingModel();
    }

    @ModelAttribute
    private ProductServiceModel productServiceModel() {
        return new ProductServiceModel();
    }


}
