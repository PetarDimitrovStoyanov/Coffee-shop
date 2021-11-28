package bg.coffeshop.coffeeShop.web.controller;

import bg.coffeshop.coffeeShop.model.service.PaymentServiceModel;
import bg.coffeshop.coffeeShop.model.binding.PaymentBindingModel;
import bg.coffeshop.coffeeShop.model.entity.Order;
import bg.coffeshop.coffeeShop.service.OrderService;
import bg.coffeshop.coffeeShop.service.PaymentService;
import bg.coffeshop.coffeeShop.util.shoppingCart.ShoppingCart;
import bg.coffeshop.coffeeShop.util.shoppingCart.ShoppingCartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("payments")
public class PaymentController {

    private final ModelMapper modelMapper;
    private final ShoppingCart shoppingCart;
    private final PaymentService paymentService;
    private final OrderService orderService;

    public PaymentController(ModelMapper modelMapper, ShoppingCart shoppingCart, PaymentService paymentService, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.shoppingCart = shoppingCart;
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @GetMapping("/payment-details")
    public String payment() {
        return "payment-details";
    }

    @PostMapping("/payment-details")
    public String paymentPost(@Valid PaymentBindingModel paymentBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        PaymentServiceModel paymentServiceModel = new PaymentServiceModel();
        if (paymentBindingModel.getPaymentType() == null) {

            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("paymentBindingModel", paymentBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.paymentBindingModel", bindingResult);
                return "redirect:payment-details";
            }

            paymentServiceModel = this.modelMapper.map(paymentBindingModel, PaymentServiceModel.class);
            paymentServiceModel.setPaymentType("bank payment");
            paymentServiceModel.setOrders(new ArrayList<>());

        } else {
            paymentServiceModel.setPaymentType("cash on courier");
            paymentServiceModel.setCvv("N/A");
            paymentServiceModel.setCardNumber("0000 0000 0000 0000");
            paymentServiceModel.setOwner("N/A");
            paymentServiceModel.setExpirationMonth("1");
            paymentServiceModel.setExpirationYear(1);
        }

        this.shoppingCart.setPayment(this.paymentService.save(paymentServiceModel));
        return "redirect:/payments/payment-done";
    }


    @GetMapping("/payment-done")
    public String done(Principal principal) throws Exception {
        if(shoppingCart.getPayment() == null || shoppingCart.getDelivery() == null){
            return "redirect:/products";
        }
        Order order = this.orderService.getOrder(principal);
        this.orderService.save(order);
        this.shoppingCart.destroy();

        return "payment-done";
    }

    @GetMapping("/cancel")
    public String cancelPayment() throws Exception {
        this.shoppingCart.destroy();
        return "redirect:/payments/shopping-cart";
    }

    @GetMapping("/shopping-cart")
    public String cart() {
        return "shopping-cart";
    }

    @PostMapping("/shopping-cart")
    public String cartPay() {
        return "redirect:/deliveries/delivery-details";
    }

    @GetMapping("/remove-it/{id}")
    public String remove(@PathVariable Long id, ShoppingCart shoppingCart) {
        for (ShoppingCartEntity item : shoppingCart.getItems()) {
            if (item.getProduct().getId() == id) {
                shoppingCart.getItems().remove(item);
                break;
            }
        }
        return "redirect:/payments/shopping-cart";
    }

    @ModelAttribute
    public PaymentBindingModel paymentBindingModel() {
        return new PaymentBindingModel();
    }

    @ModelAttribute
    private ShoppingCart shoppingCart() {
        return this.shoppingCart;
    }


}
