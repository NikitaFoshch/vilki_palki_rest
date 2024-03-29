package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.model.user.UserAuthRequest;
import lab.space.vilki_palki_rest.model.user.UserRequest;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    void sendCodeByUserByEmail(UserRequest request);
    ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response);

    ResponseEntity<?> authentication(UserAuthRequest request);

}
