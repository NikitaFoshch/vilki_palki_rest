//package lab.space.vilki_palki_rest.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lab.space.vilki_palki_rest.config.JwtAuthenticationEntryPoint;
//import lab.space.vilki_palki_rest.config.JwtAuthenticationFilter;
//import lab.space.vilki_palki_rest.config.SecurityConfig;
//import lab.space.vilki_palki_rest.model.banner.BannerResponse;
//import lab.space.vilki_palki_rest.service.BannerService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class BannerControllerTest {
//    @LocalServerPort
//    private Integer port;
//
//    private static RestTemplate restTemplate;
//
//    private final String DEFAULT_PATH = "https://localhost:"+ port+"/banners";
//
//    @Test
//    void getAllBanners() throws Exception {
//        List<BannerResponse> expectedBanners = new ArrayList<>();
//        expectedBanners.add(BannerResponse.builder().name("sghdhaa").build());
//        expectedBanners.add(BannerResponse.builder().name("sghdh").build());
//        ResponseEntity<BannerResponse> bannerResponses = restTemplate.getForEntity(DEFAULT_PATH + "/get-all-banners",BannerResponse.class);
//    }
//}