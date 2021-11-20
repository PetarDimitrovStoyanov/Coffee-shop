package bg.coffeshop.coffeeShop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("payments")
public class PaymentController {

    @GetMapping("/shopping-cart")
    public String cart() {
        return "shopping-cart";
    }

    @GetMapping("payment-details")
    public String payment() {
        return "payment-details";
    }

    @GetMapping("payment-done")
    public String done() {
        return "payment-done";
    }

}
