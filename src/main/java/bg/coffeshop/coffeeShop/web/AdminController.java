package bg.coffeshop.coffeeShop.web;

import bg.coffeshop.coffeeShop.model.Service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.binding.ProductBindingModel;
import bg.coffeshop.coffeeShop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/edit-product/{id}")
    public String edit(@PathVariable Long id, Model model) {
        ProductServiceModel product = this.productService.findById(id);
        model.addAttribute("productServiceModel", product);
        return "/edit-product";
    }

    @PostMapping("/edit-product/{id}")
    public String editPost(@Valid ProductServiceModel productServiceModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @RequestParam("file") MultipartFile file, @PathVariable Long id) throws IOException {
        System.out.println(id);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productServiceModel", productServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productServiceModel", bindingResult);
            return "/edit-product";
        }

        boolean productExists = this.productService.findById(productServiceModel.getId()) != null;
        if (!productExists) {
            return "redirect:/edit-product";
        }
        ProductServiceModel productServiceModel2 = mapProductServiceModel(productServiceModel, file);
        this.productService.update(productServiceModel2, id);
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) throws Exception {
        this.productService.deleteById(id);
        return "redirect:/products";
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

        ProductServiceModel productServiceModel = mapProductServiceModel(productBindingModel, file);

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

    @ModelAttribute
    private ProductServiceModel productServiceModel() {
        return new ProductServiceModel();
    }

    private ProductServiceModel mapProductServiceModel(@Valid ProductBindingModel productBindingModel, @RequestParam("file") MultipartFile file) throws IOException {
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(productBindingModel.getName());
        productServiceModel.setPrice(productBindingModel.getPrice());
        productServiceModel.setType(productBindingModel.getType());

        productServiceModel
                .setOrders(new ArrayList<>());

        if (!file.isEmpty()) {
            productServiceModel.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
        }

        return productServiceModel;
    }

    private ProductServiceModel mapProductServiceModel(@Valid ProductServiceModel productServiceModel1, @RequestParam("file") MultipartFile file) throws IOException {
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(productServiceModel1.getName());
        productServiceModel.setPrice(productServiceModel1.getPrice());
        productServiceModel.setType(productServiceModel1.getType());
        productServiceModel.setOrders(productServiceModel1.getOrders());

        if (!file.isEmpty()) {
            productServiceModel.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
        } else {
            ProductServiceModel product = this.productService.findById(productServiceModel1.getId());
            productServiceModel.setPicture(product.getPicture());
        }

        return productServiceModel;
    }

}
