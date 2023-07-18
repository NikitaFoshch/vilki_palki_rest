package lab.space.vilki_palki_rest.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {

    String extractUsername(String token);

    Map<String, String> generateTokens(UserDetails userDetails);
    Map<String, String> generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

}