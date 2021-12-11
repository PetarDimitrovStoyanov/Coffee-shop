package bg.coffeshop.coffeeShop.web.controller;
import bg.coffeshop.coffeeShop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "admin", roles = "ADMINISTRATOR")
    @Test
    public void admin() throws Exception {
        mockMvc.perform(get("/admin/admin"))
                .andExpect(status().is(200));
    }

    @WithMockUser(username = "admin", roles = "ADMINISTRATOR")
    @Test
    public void addProductPage() throws Exception {
        mockMvc.perform(get("/admin/add-product"))
                .andExpect(status().is(200));
    }

    @WithMockUser(username = "admin", roles = "ADMINISTRATOR")
    @Test
    public void allUsersProductPage() throws Exception {
        mockMvc.perform(get("/admin/all-users"))
                .andExpect(status().is(200));
    }

}