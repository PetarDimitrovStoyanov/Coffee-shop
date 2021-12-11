package bg.coffeshop.coffeeShop.web.controller;

import bg.coffeshop.coffeeShop.model.service.ProductServiceModel;
import bg.coffeshop.coffeeShop.model.binding.ProductBindingModel;
import bg.coffeshop.coffeeShop.model.view.OrderViewModel;
import bg.coffeshop.coffeeShop.model.view.UserViewModel;
import bg.coffeshop.coffeeShop.service.OrderService;
import bg.coffeshop.coffeeShop.service.ProductService;
import bg.coffeshop.coffeeShop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;

    public AdminController(ProductService productService, UserService userService, OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
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

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productServiceModel", productServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productServiceModel", bindingResult);

            return "/edit-product";
        }

        boolean productExists = this.productService.findById(productServiceModel.getId()) != null;
        if (!productExists) {

            return "redirect:/edit-product";
        }
        ProductServiceModel productServiceModel2 = this.productService.mapProductServiceModel(productServiceModel, file);
        productServiceModel2.setPiece(0);
        System.out.println();
        this.productService.update(productServiceModel2, id);

        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) throws Exception {
        this.productService.deleteById(id);

        return "redirect:/products";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<OrderViewModel> orders = this.orderService.findAll();
        model.addAttribute("orders", orders);

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
        }

        ProductServiceModel productServiceModel = this.productService.mapProductServiceModel(productBindingModel, file);

        this.productService.saveInDB(productServiceModel);
        return "redirect:/products";
    }

    @GetMapping("/all-users")
    public String allUsers(Model model) {
        List<UserViewModel> users = this.userService.findAll();

        model.addAttribute("users", users);
        return "all-users";
    }

    @PostMapping("/all-users")
    public String inputSearchUser(@RequestParam String text, Model model) {
        List<UserViewModel> users = this.userService.findByNameCriteria(text);

        model.addAttribute("users", users);
        model.addAttribute("text", text);
        return "all-users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return "redirect:/admin/all-users";
    }

    @GetMapping("/change/{id}")
    public String changeUserRole(@PathVariable Long id) throws Exception {
        this.userService.changeCurrentUserRole(id);
        return "redirect:/admin/all-users";
    }

    @ModelAttribute
    private ProductBindingModel productBindingModel() {
        return new ProductBindingModel();
    }

    @ModelAttribute
    private ProductServiceModel productServiceModel() {
        return new ProductServiceModel();
    }

}
