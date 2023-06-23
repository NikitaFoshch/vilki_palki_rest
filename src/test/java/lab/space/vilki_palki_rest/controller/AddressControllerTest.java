package lab.space.vilki_palki_rest.controller;

import lab.space.vilki_palki_rest.config.JwtAuthenticationEntryPoint;
import lab.space.vilki_palki_rest.config.JwtAuthenticationFilter;
import lab.space.vilki_palki_rest.repository.AddressRepository;
import lab.space.vilki_palki_rest.service.AddressService;
import lab.space.vilki_palki_rest.service.JwtService;
import lab.space.vilki_palki_rest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@Import(SecurityConfig.class)
//@WebMvcTest(AddressController.class)
//@WithMockUser
@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @MockBean
    private UserService userService;

    @MockBean
    private AddressRepository addressRepository;
    private final String DEFAULT_PATH = "/addresses";

    @Test
    void getAddress() throws Exception {
    }

    @Test
    void getAllAddresses() throws Exception {
    }

    @Test
    void saveAddress() {
    }

    @Test
    void updateAddress() {
    }

    @Test
    void testUpdateAddress() {
    }
}