package lab.space.vilki_palki_rest.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lab.space.vilki_palki_rest.service.JwtService;
import lab.space.vilki_palki_rest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {
    private final UserService userService;

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;
    private final int ACCESS_JWT_EXPIRED_TIME = 6;

    private final int REFRESH_JWT_EXPIRED_TIME = 6;

    @Override
    public String extractUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    @Override
    public Map<String, String> generateTokens(UserDetails userDetails) {
        String accessToken = JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 604800017L * ACCESS_JWT_EXPIRED_TIME))
                .sign(getSignInAlgorithm());
        String refreshToken = JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 600000000L * REFRESH_JWT_EXPIRED_TIME))
                .sign(getSignInAlgorithm());
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        userService.addUserPass(userDetails.getUsername());
        return tokens;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            JWT.require(getSignInAlgorithm()).withSubject(userDetails.getUsername()).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.warn(e.getMessage());
            return false;
        }
    }

    private Algorithm getSignInAlgorithm() {
        return Algorithm.HMAC256(Base64.getDecoder().decode(JWT_SECRET));
    }

}
