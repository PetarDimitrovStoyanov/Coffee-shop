package bg.coffeshop.coffeeShop.web.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RestApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllProductsTest() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().is(200));
    }

    @Test
    public void getStatisticTest() throws Exception {
        mockMvc.perform(get("/api/statistic"))
                .andExpect(status().is(200));
    }
}