//package lab.space.vilki_palki_rest.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lab.space.vilki_palki_rest.config.SecurityConfig;
//import lab.space.vilki_palki_rest.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Import(SecurityConfig.class)
//@WebMvcTest(UserController.class)
//@WithMockUser
//public class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private UserService userService;
//    private final String DEFAULT_PATH = "/Vilki_Palki_Admin/user";
//
//    @Test
//    public void testGetUser_ReturnsUserResponse_WhenUserExists() throws Exception {
//        mockMvc.perform(
//                get(DEFAULT_PATH + "/get-user/" + 9)
//        ).andDo(print()).andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetUser_ReturnsNotFound_WhenUserDoesNotExist() throws Exception {
//        mockMvc.perform(
//                get(DEFAULT_PATH + "/get-user/" + 1)
//        ).andDo(print()).andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testUpdateUser_ReturnsOk_WhenUpdateIsSuccessful() {
//    }
//
//    @Test
//    public void testUpdateUser_ReturnsBadRequest_WhenValidationFails() {
//    }
//}
