package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.User;
import lab.space.vilki_palki_rest.model.user.UserResponse;
import lab.space.vilki_palki_rest.model.user.UserUpdateRequest;

public interface UserService {
    User getUserById(Long id);
    UserResponse getCurrentUser();

    User getUserByEmail(String email);

    UserResponse getUserDto(Long id);

    void saveUser(String email);

    void updateUser(UserUpdateRequest requestUser, String email);

    String  addUserPass(String email);

}
