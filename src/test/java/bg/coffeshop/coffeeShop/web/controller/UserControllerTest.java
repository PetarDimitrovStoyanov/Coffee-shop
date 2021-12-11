package bg.coffeshop.coffeeShop.web.controller;

import bg.coffeshop.coffeeShop.model.entity.Product;
import bg.coffeshop.coffeeShop.repository.ProductRepository;
import bg.coffeshop.coffeeShop.repository.RoleRepository;
import bg.coffeshop.coffeeShop.repository.UserEntityRepository;
import bg.coffeshop.coffeeShop.util.shoppingCart.ShoppingCart;
import bg.coffeshop.coffeeShop.util.shoppingCart.ShoppingCartEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ShoppingCart shoppingCart;
    private ModelMapper modelMapper;
    private Product product;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserEntityRepository userEntityRepository;
    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){
        List<ShoppingCartEntity> list = new ArrayList<>();
        shoppingCart = new ShoppingCart(list);
        modelMapper = new ModelMapper();

        product = new Product();
        product.setType("Grinded")
                .setPicture(null)
                .setName("Sweet hart")
                .setPrice(BigDecimal.valueOf(20))
                .setPiece(0);
        product = productRepository.saveAndFlush(product);
    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
        userEntityRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void firstTest() throws Exception {
        mockMvc.perform(get("/order-it/" + product.getId()))
                .andExpect(status().is(302));
    }

    @Test
    public void homepageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(200));
    }

    @Test
    public void homepageTwoPageTest() throws Exception {
        mockMvc.perform(get("/homepage"))
                .andExpect(status().is(200));
    }

    @WithMockUser("spring")
    @Test
    public void aboutPageTest() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().is(200));
    }

    @WithMockUser("spring")
    @Test
    public void moonmilkPageTest() throws Exception {
        mockMvc.perform(get("/moonmilk"))
                .andExpect(status().is(200));
    }

    @WithMockUser("spring")
    @Test
    public void locationOnePageTest() throws Exception {
        mockMvc.perform(get("/location-one"))
                .andExpect(status().is(200));
    }

    @WithMockUser("spring")
    @Test
    public void locationTwoPageTest() throws Exception {
        mockMvc.perform(get("/location-two"))
                .andExpect(status().is(200));
    }

    @WithMockUser("spring")
    @Test
    public void locationThreePageTest() throws Exception {
        mockMvc.perform(get("/location-three"))
                .andExpect(status().is(200));
    }

    @WithMockUser("spring")
    @Test
    public void products() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }


}