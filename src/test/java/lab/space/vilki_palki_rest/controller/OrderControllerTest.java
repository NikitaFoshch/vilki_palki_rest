package lab.space.vilki_palki_rest.controller;

import lab.space.vilki_palki_rest.config.JwtAuthenticationEntryPoint;
import lab.space.vilki_palki_rest.config.JwtAuthenticationFilter;
import lab.space.vilki_palki_rest.config.SecurityConfig;
import lab.space.vilki_palki_rest.service.JwtService;
import lab.space.vilki_palki_rest.service.OrderService;
import lab.space.vilki_palki_rest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(OrderController.class)

class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @MockBean
    private UserService userService;
    @Test
    void getOrder() throws Exception {
    }

    @Test
    void getAllOrders() {
    }

    @Test
    void testGetAllOrders() {
    }
}