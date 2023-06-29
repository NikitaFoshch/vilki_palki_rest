package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.model.user.UserAuthRequest;
import lab.space.vilki_palki_rest.model.user.UserRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    void sendCodeByUserByEmail(UserRequest request);

    ResponseEntity<?> authentication(UserAuthRequest request);

}
