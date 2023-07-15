package lab.space.vilki_palki_rest.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lab.space.vilki_palki_rest.service.UserService;
import lab.space.vilki_palki_rest.service.impl.JwtServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class JwtServiceImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private JwtServiceImpl jwtService;

    @Test
    public void testExtractUsername_ValidToken_ReturnsUsername() {
        String token = createValidToken("test@example.com");

        String result = jwtService.extractUsername(token);

        assertThat(result).isEqualTo("test@example.com");
    }

    @Test
    public void testGenerateTokens_ReturnsAccessTokenAndRefreshToken() {
        UserDetails userDetails = new User("test@example.com", "password", Collections.emptyList());

        Map<String, String> tokens = jwtService.generateTokens(userDetails);

        assertThat(tokens).containsKeys("access_token", "refresh_token");
        assertThat(tokens.get("access_token")).isNotEmpty();
        assertThat(tokens.get("refresh_token")).isNotEmpty();
        verify(userService, times(1)).addUserPass(userDetails.getUsername());
    }

    @Test
    public void testIsTokenValid_ValidToken_ReturnsTrue() {
        UserDetails userDetails = new User("test@example.com", "password", Collections.emptyList());
        Map<String, String> tokens = jwtService.generateTokens(userDetails);

        boolean result = jwtService.isTokenValid(tokens.get("access_token"), userDetails);

        assertThat(result).isTrue();
    }

    @Test
    public void testIsTokenValid_InvalidToken_ReturnsFalse() {
        String token = "invalid_token";
        UserDetails userDetails = new User("test@example.com", "password", Collections.emptyList());

        boolean result = jwtService.isTokenValid(token, userDetails);

        assertThat(result).isFalse();
    }

    private String createValidToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + 3600 * 1000); // 1 hour
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }
}
