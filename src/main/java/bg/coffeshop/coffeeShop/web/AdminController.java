package bg.coffeshop.coffeeShop.web;

import bg.coffeshop.coffeeShop.model.Service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.binding.ProductBindingModel;
import bg.coffeshop.coffeeShop.model.binding.UserRegisterBindingModel;
import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.model.view.ProductViewModel;
import bg.coffeshop.coffeeShop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

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

    @PostMapping("/add-product")
    public String addFile(@Valid ProductBindingModel productBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes,
                          @RequestParam("file") MultipartFile file) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productBindingModel", productBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productBindingModel", bindingResult);
            return "redirect:add-product";
        }

        boolean productExists = this.productService.isProductExists(productBindingModel.getName());
        if (productExists) {
            return "redirect:add-product";
            //TODO: CUSTOM VALIDATION & ON REGISTER
        }

        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(productBindingModel.getName());
        productServiceModel.setPrice(productBindingModel.getPrice());
        productServiceModel.setType(productBindingModel.getType());
        productServiceModel.setStatus(productBindingModel.getStatusString().equals("Active"));

        productServiceModel
                .setOrders(new ArrayList<>())
                .setPicture(Base64.getEncoder().encodeToString(file.getBytes()));

        this.productService.saveInDB(productServiceModel);
        return "redirect:/products";
    }

    @GetMapping("/all-users")
    public String allUsers() {
        return "all-users";
    }

    @ModelAttribute
    private ProductBindingModel productBindingModel() {
        return new ProductBindingModel();
    }
}
