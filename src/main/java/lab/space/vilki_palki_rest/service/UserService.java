package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.User;
import lab.space.vilki_palki_rest.model.user.UserResponse;
import lab.space.vilki_palki_rest.model.user.UserUpdateRequest;

public interface UserService {
    User getUserById(Long id);

    User getUserByEmail(String email);

    UserResponse getUserDto(Long id);

    void saveUser(String email);

    void updateUser(UserUpdateRequest user);

    void  addUserPass(String email, String pass);
    void  removeUserPass(String email);

}
