package bg.coffeshop.coffeeShop.web.controller;

import bg.coffeshop.coffeeShop.model.binding.PaymentBindingModel;
import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.repository.PaymentRepository;
import bg.coffeshop.coffeeShop.util.shoppingCart.ShoppingCart;
import bg.coffeshop.coffeeShop.util.shoppingCart.ShoppingCartEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PaymentRepository paymentRepository;
    List<ShoppingCartEntity> list = new ArrayList<>();
    private ShoppingCartEntity shoppingCartEntity;
    private ShoppingCart shoppingCart;
    private PaymentBindingModel paymentBindingModel;

    @BeforeEach
    void setUp() {
        paymentBindingModel = new PaymentBindingModel();
        paymentBindingModel.setPaymentType("card");
        paymentBindingModel.setCvv("cvv");
        paymentBindingModel.setOwner("owner");
        paymentBindingModel.setCardNumber("0000 0000 0000 0000");
        paymentBindingModel.setExpirationMonth("January");
        paymentBindingModel.setExpirationYear(2026);
        shoppingCart = new ShoppingCart(list);
        shoppingCartEntity = new ShoppingCartEntity();
        shoppingCartEntity.setPiece(2);
        shoppingCartEntity.setPrice(BigDecimal.valueOf(2.20));
        shoppingCartEntity.setTotal(BigDecimal.valueOf(4.40));

        Product product = new Product();
        product.setId(1L);
        product.setPicture("some picture");
        product.setPrice(BigDecimal.valueOf(2));
        product.setName("some name");
        product.setType("Grinded");
        shoppingCartEntity.setProduct(product);
        list.add(shoppingCartEntity);
        shoppingCart.setItems(list);
    }

    @AfterEach
    void tearDown() {
        shoppingCartEntity = new ShoppingCartEntity();
        shoppingCart = new ShoppingCart(list);
        paymentRepository.deleteAll();
    }

    @WithMockUser("spring")
    @Test
    public void paymentPage() throws Exception {
        mockMvc.perform(get("/payments/payment-details"))
                .andExpect(status().is(200));
    }

    @WithMockUser("spring")
    @Test
    public void shoppingCartPage() throws Exception {
        mockMvc.perform(get("/payments/shopping-cart"))
                .andExpect(status().is(200));
    }

    @WithMockUser("spring")
    @Test
    public void shoppingCartCancelPage() throws Exception {
        mockMvc.perform(get("/payments/cancel"))
                .andExpect(status().is(302));
    }

    @WithMockUser("spring")
    @Test
    public void remove() throws Exception {
        mockMvc.perform(get("/payments/remove-it/" + 1L))
                .andExpect(status().is(302));
    }
}