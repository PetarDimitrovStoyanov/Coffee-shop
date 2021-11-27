package bg.coffeshop.coffeeShop.web;

import bg.coffeshop.coffeeShop.constant.RoleEnum;
import bg.coffeshop.coffeeShop.model.binding.UserRegisterBindingModel;
import bg.coffeshop.coffeeShop.model.entity.Role;
import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import bg.coffeshop.coffeeShop.repository.RoleRepository;
import bg.coffeshop.coffeeShop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RoleRepository roleService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(UserService userService, ModelMapper mapper, RoleRepository roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.modelMapper = mapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()
                || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())
                || this.userService.isUserExists(userRegisterBindingModel)) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "register";
        }
        UserEntity userEntity = this.modelMapper.map(userRegisterBindingModel, UserEntity.class);


        List<Role> roles = new ArrayList<>();
        Role role = this.roleService.findRoleByName(RoleEnum.USER);
        roles.add(role);

        userEntity.setRoles(roles);
        userEntity.setOrders(new ArrayList<>());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        this.userService.saveInDB(userEntity);

        return "redirect:login";
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

    @ModelAttribute
    private UserRegisterBindingModel registerUserBindingModel() {
        return new UserRegisterBindingModel();
    }

}


