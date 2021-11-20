package bg.coffeshop.coffeeShop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deliveries")
public class DeliveryController {

    @GetMapping("/delivery-details")
    public String delivery() {
        return "delivery-details";
    }
}
