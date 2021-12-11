package bg.coffeshop.coffeeShop.web.controller;

import bg.coffeshop.coffeeShop.model.service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.model.view.ProductViewModel;
import bg.coffeshop.coffeeShop.service.ProductService;
import bg.coffeshop.coffeeShop.service.StatisticService;
import bg.coffeshop.coffeeShop.service.UserService;
import bg.coffeshop.coffeeShop.util.shoppingCart.ShoppingCart;
import bg.coffeshop.coffeeShop.util.shoppingCart.ShoppingCartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class UserController {

    private final ShoppingCart shoppingCart;
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final StatisticService statisticService;
    private final UserService userService;

    public UserController(ShoppingCart shoppingCart, ProductService productService, ModelMapper modelMapper, StatisticService statisticService, UserService userService) {
        this.shoppingCart = shoppingCart;
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.statisticService = statisticService;
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
    public String products(Model model, HttpServletResponse response) {
        List<ProductViewModel> productViewModels = this.productService.findAll();
        model.addAttribute("products", productViewModels);
        this.statisticService.saveVisitationInDataBase(response.getHeader("visitor"));

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

    @GetMapping("/order-it/{id}")
    public String orderIt(@PathVariable Long id, Model model) {
        ProductServiceModel productServiceModel = this.modelMapper
                .map(this.productService.findById(id), ProductServiceModel.class);
        model.addAttribute("productServiceModel", productServiceModel);
        return "order-it";
    }

    @PostMapping("/order-it/{id}")
    public String addToBasket(ProductServiceModel productServiceModel, @PathVariable Long id) {
        if (productServiceModel.getPiece() == null || productServiceModel.getPiece() <= 0) {
            return String.format("redirect:/order-it/%s", id);
        }

        Product product = this.modelMapper.map(this.productService.findById(id), Product.class);
        ShoppingCartEntity entity = setShoppingCartData(productServiceModel, product);

        boolean isPresented = false;
        ShoppingCartEntity shoppingCartEntity = null;
        for (ShoppingCartEntity item : shoppingCart.getItems()) {
            if (item.getProduct().getId() == id) {
                isPresented = true;
                shoppingCartEntity = item;
                break;
            }
        }

        if (isPresented) {
            shoppingCartEntity.setPiece(shoppingCartEntity.getPiece() + entity.getPiece());
            return "redirect:/products";
        }

        this.shoppingCart.getItems().add(entity);
        return "redirect:/products";
    }

    @ModelAttribute
    private ProductServiceModel productServiceModel() {
        return new ProductServiceModel();
    }

    private ShoppingCartEntity setShoppingCartData(ProductServiceModel productServiceModel, Product product) {
        ShoppingCartEntity entity = new ShoppingCartEntity();
        entity.setPiece(productServiceModel.getPiece());
        entity.setPrice(product.getPrice());
        entity.setProduct(product);
        return entity;
    }

}
