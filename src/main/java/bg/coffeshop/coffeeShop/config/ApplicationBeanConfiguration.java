package bg.coffeshop.coffeeShop.config;

import bg.coffeshop.coffeeShop.util.ShoppingCart;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ShoppingCart shoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart(new ArrayList<>());
        return shoppingCart;
    }
}

