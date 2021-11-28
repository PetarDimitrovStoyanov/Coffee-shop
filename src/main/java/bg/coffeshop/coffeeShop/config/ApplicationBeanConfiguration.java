package bg.coffeshop.coffeeShop.config;

import bg.coffeshop.coffeeShop.util.ShoppingCart;
import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Map;

@Configuration
public class ApplicationBeanConfiguration {
    private final CloudinaryConfig cloudinaryConfig;

    public ApplicationBeanConfiguration(CloudinaryConfig cloudinaryConfig) {
        this.cloudinaryConfig = cloudinaryConfig;
    }

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

    @Bean
    public Cloudinary cloudinary() {
   /*     return new Cloudinary(Map.of(
                        "cloud_name", cloudinaryConfig.getCloudName(),
                        "api_key", "559387692836932",
                        "api_secret", cloudinaryConfig.getApiSecret()));*/
        return new Cloudinary();
    }

}

