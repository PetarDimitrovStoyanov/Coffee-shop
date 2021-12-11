package bg.coffeshop.coffeeShop.web.controller;

import bg.coffeshop.coffeeShop.model.binding.DeliveryBindingModel;
import bg.coffeshop.coffeeShop.repository.DeliveryRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DeliveryRepository deliveryRepository;
    List<ShoppingCartEntity> list = new ArrayList<>();
    private ShoppingCart shoppingCart = new ShoppingCart(list);

    DeliveryBindingModel deliveryBindingModel = new DeliveryBindingModel();

    @BeforeEach
    void setUp() {
        deliveryBindingModel.setAddress("Some address");
        deliveryBindingModel.setCity("Some city");
        deliveryBindingModel.setCountry("Some country");
        deliveryBindingModel.setPostalCode("4000");
        deliveryBindingModel.setCourier("DHL");
        deliveryBindingModel.setEmail("email@abv.bg");
        deliveryBindingModel.setPhone("+359888888888");
        deliveryBindingModel.setPerson("Some Person");
    }

    @AfterEach
    void tearDown() {
        deliveryRepository.deleteAll();
    }

    @WithMockUser("spring")
    @Test
    public void deliveryPost() throws Exception {
        mockMvc.perform(post("/deliveries/delivery-details")
                        .param("address", deliveryBindingModel.getAddress())
                        .param("city", deliveryBindingModel.getCity())
                        .param("country", deliveryBindingModel.getCountry())
                        .param("postalCode", deliveryBindingModel.getPostalCode())
                        .param("email", deliveryBindingModel.getEmail())
                        .param("person", deliveryBindingModel.getPerson())
                        .param("phone", deliveryBindingModel.getPhone())
                        .param("courier", deliveryBindingModel.getCourier())
                        .with(csrf())
                )
                .andExpect(status().is(302));
        assertEquals(1, deliveryRepository.count());
    }

    @WithMockUser("spring")
    @Test
    public void deliveryErrorPost() throws Exception {
        mockMvc.perform(post("/deliveries/delivery-details")
                        .param("address", deliveryBindingModel.getAddress())
                        .param("city", "")
                        .param("country", deliveryBindingModel.getCountry())
                        .param("postalCode", deliveryBindingModel.getPostalCode())
                        .param("email", deliveryBindingModel.getEmail())
                        .param("person", deliveryBindingModel.getPerson())
                        .param("phone", deliveryBindingModel.getPhone())
                        .param("courier", deliveryBindingModel.getCourier())
                        .with(csrf())

                )
                .andExpect(status().is(302));
        assertEquals(0, deliveryRepository.count());
    }


    @WithMockUser("spring")
    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/deliveries/delivery-details"))
                .andExpect(status().is(200));
    }

}