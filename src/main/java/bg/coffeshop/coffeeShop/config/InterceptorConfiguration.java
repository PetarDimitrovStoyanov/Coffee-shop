package bg.coffeshop.coffeeShop.config;

import bg.coffeshop.coffeeShop.web.interceptor.OrderDoneInterceptor;
import bg.coffeshop.coffeeShop.web.interceptor.ProductPageVisitationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OrderDoneInterceptor()).addPathPatterns("/payments/payment-done");
        registry.addInterceptor(new ProductPageVisitationInterceptor()).addPathPatterns("/products");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
