package bg.coffeshop.coffeeShop.web;

import bg.coffeshop.coffeeShop.model.Service.DeliveryServiceModel;
import bg.coffeshop.coffeeShop.model.binding.DeliveryBindingModel;
import bg.coffeshop.coffeeShop.service.DeliveryService;
import bg.coffeshop.coffeeShop.util.ShoppingCart;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/deliveries")
public class DeliveryController {

    private final ModelMapper modelMapper;
    private final DeliveryService deliveryService;
    private final ShoppingCart shoppingCart;

    public DeliveryController(ModelMapper modelMapper, DeliveryService deliveryService, ShoppingCart shoppingCart) {
        this.modelMapper = modelMapper;
        this.deliveryService = deliveryService;
        this.shoppingCart = shoppingCart;
    }

    @PostMapping("/delivery-details")
    public String deliveryPost(@Valid DeliveryBindingModel deliveryBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("deliveryBindingModel", deliveryBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.deliveryBindingModel", bindingResult);
            return "redirect:delivery-details";
        }

        DeliveryServiceModel deliveryServiceModel = this.modelMapper.map(deliveryBindingModel, DeliveryServiceModel.class);
        deliveryServiceModel.setOrders(new ArrayList<>());

        this.shoppingCart.setDelivery(this.deliveryService.save(deliveryServiceModel));

        return "redirect:/payments/payment-details";
    }

    @GetMapping("/delivery-details")
    public String delivery(){
        return "delivery-details";
    }

    @ModelAttribute
    public DeliveryBindingModel deliveryBindingModel() {
        return new DeliveryBindingModel();
    }
}
