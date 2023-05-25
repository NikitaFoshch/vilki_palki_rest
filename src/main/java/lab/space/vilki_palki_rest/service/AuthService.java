package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.model.user.UserAuthRequest;
import lab.space.vilki_palki_rest.model.user.UserRequest;

public interface AuthService {
    void sendCodeByUserByEmail(UserRequest request);

    void authentication(UserAuthRequest request);

}
