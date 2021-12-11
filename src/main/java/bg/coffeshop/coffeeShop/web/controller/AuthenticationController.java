package bg.coffeshop.coffeeShop.web.controller;

import bg.coffeshop.coffeeShop.model.service.UserRegistrationServiceModel;
import bg.coffeshop.coffeeShop.model.binding.UserRegisterBindingModel;
import bg.coffeshop.coffeeShop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute(name = "model") UserRegisterBindingModel model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasErrors()
                || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())
                || this.userService.isUserExists(userRegisterBindingModel)) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "register";
        }

        UserRegistrationServiceModel userRegistrationServiceModel =
                this.userService.getUserRegistrationServiceModel(userRegisterBindingModel);
        this.userService.register(userRegistrationServiceModel);

        return "redirect:login";
    }

    @ModelAttribute
    private UserRegisterBindingModel registerUserBindingModel() {
        return new UserRegisterBindingModel();
    }

}


