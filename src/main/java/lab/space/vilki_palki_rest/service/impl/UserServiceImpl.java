package lab.space.vilki_palki_rest.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki_rest.entity.User;
import lab.space.vilki_palki_rest.mapper.UserMapper;
import lab.space.vilki_palki_rest.model.user.UserResponse;
import lab.space.vilki_palki_rest.model.user.UserUpdateRequest;
import lab.space.vilki_palki_rest.repository.UserRepository;
import lab.space.vilki_palki_rest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found by id " + id));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public UserResponse getUserDto(Long id) {
        return userMapper.toDto(getUserById(id));
    }

    @Override
    public void saveUser(String email) {
        userRepository.save(new User().setEmail(email));
    }

    @Override
    public void updateUser(UserUpdateRequest requestUser) {
        userRepository.save(
                getUserById(requestUser.id())
                        .setEmail(requestUser.email())
                        .setName(requestUser.name())
                        .setBirthday(requestUser.birthday())
                        .setPhone(requestUser.phone())
                        .setBonusPoints(requestUser.bonusPoints())
                        .setFacebookId(requestUser.facebookId())
                        .setCardNumber(requestUser.cardNumber())
        );
    }

    @Override
    public void addUserPass(String email, String pass) {
        userRepository.addPasswordByUserEmail(email, pass);
    }

    @Override
    public void removeUserPass(String email) {
        userRepository.removePasswordByUserEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = getUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                userDetails.getUsername(), userDetails.getPassword(), new ArrayList<>()
        );
    }
}
