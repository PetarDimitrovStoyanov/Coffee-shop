package bg.coffeshop.coffeeShop.web.controller;

import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import bg.coffeshop.coffeeShop.repository.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @BeforeEach
    void setUp() {
        userEntityRepository.deleteAll();
    }

    @Test
    public void loginPageTest() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpect(status().is(200));
    }

    @Test
    public void homepageOnePageTest() throws Exception {
        mockMvc.perform(get("/auth/register"))
                .andExpect(status().is(200));
    }

    private static final String TEST_USER_EMAIL = "petar@abv.bg";
    private static final String TEST_USER_PASSWORD = "asd";
    private static final String TEST_USER_CONFIRM_PASSWORD = "asd";
    private static final String TEST_USER_PHONE_NUMBER = "+359888888888";
    private static final String TEST_USER_AGE = "18";
    private static final String TEST_USER_GENDER = "MALE";
    private static final String TEST_USER_USERNAME = "petarStoyanov";
    private static final String TEST_USER_LASTNAME = "Stoyanov";
    private static final String TEST_USER_FIRSTNAME = "Petar";

    @Test
    public void registerPostTest() throws Exception {
        mockMvc.perform(post("/auth/register")
                .param("firstName", TEST_USER_FIRSTNAME)
                .param("lastName", TEST_USER_LASTNAME)
                .param("username", TEST_USER_USERNAME)
                .param("gender", TEST_USER_GENDER)
                .param("email", TEST_USER_EMAIL)
                .param("password", TEST_USER_PASSWORD)
                .param("confirmPassword", TEST_USER_CONFIRM_PASSWORD)
                .param("phoneNumber", TEST_USER_PHONE_NUMBER)
                .param("age", TEST_USER_AGE)
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().is3xxRedirection());
        assertEquals(1, userEntityRepository.count());
        UserEntity userEntity = this.userEntityRepository.findByEmail(TEST_USER_EMAIL).orElseThrow(Exception::new);
        assertEquals(userEntity.getAge(), Integer.parseInt(TEST_USER_AGE));
    }

    @Test
    public void registerFailPostTest() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .param("firstName", TEST_USER_FIRSTNAME)
                        .param("lastName", TEST_USER_LASTNAME)
                        .param("username", TEST_USER_USERNAME)
                        .param("gender", TEST_USER_GENDER)
                        .param("email", "")
                        .param("password", TEST_USER_PASSWORD)
                        .param("confirmPassword", TEST_USER_CONFIRM_PASSWORD)
                        .param("phoneNumber", TEST_USER_PHONE_NUMBER)
                        .param("age", TEST_USER_AGE)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().isOk());
        assertEquals(0, userEntityRepository.count());
    }

}