package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.User;
import lab.space.vilki_palki_rest.mapper.UserMapper;
import lab.space.vilki_palki_rest.model.user.UserResponse;
import lab.space.vilki_palki_rest.model.user.UserUpdateRequest;
import lab.space.vilki_palki_rest.repository.UserRepository;
import lab.space.vilki_palki_rest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Lazy))
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found by id " + id));
    }

    @Override
    public UserResponse getCurrentUser() {
        return userMapper.toDto(
                getUserByEmail(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                )
        );
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found by email " + email));
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
    public void updateUser(UserUpdateRequest requestUser, String email) {
        userRepository.save(
                getUserById(getUserByEmail(email).getId())
                        .setEmail(requestUser.getEmail())
                        .setName(requestUser.getName())
                        .setBirthday(requestUser.getBirthday())
                        .setPhone(requestUser.getPhone())
                        .setBonusPoints(requestUser.getBonusPoints())
                        .setFacebookId(requestUser.getFacebookId())
                        .setCardNumber(requestUser.getCardNumber())
        );
    }

    @Override
    public String addUserPass(String email) {
        String code = generateVerificationCode();
        userRepository.addPasswordByUserEmail(email, passwordEncoder.encode(code));
        return code;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = getUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                userDetails.getUsername(), userDetails.getPassword(), new ArrayList<>()
        );
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        return String.valueOf(code);
    }
}
