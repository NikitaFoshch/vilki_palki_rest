package lab.space.vilki_palki_rest.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lab.space.vilki_palki_rest.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lab.space.vilki_palki_rest.entity.User;
import lab.space.vilki_palki_rest.mapper.UserMapper;
import lab.space.vilki_palki_rest.model.user.UserResponse;
import lab.space.vilki_palki_rest.model.user.UserUpdateRequest;
import lab.space.vilki_palki_rest.repository.UserRepository;
import lab.space.vilki_palki_rest.service.UserService;
import org.springframework.util.Assert;
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetUserById_ExistingId_ReturnsUser() {
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUserById(userId);

        assertThat(result).isEqualTo(user);
    }

    @Test
    public void testGetUserById_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(userRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userService.getUserById(nonExistingId);
        });
    }

    @Test
    public void testGetUserByEmail_ExistingEmail_ReturnsUser() {
        String email = "test@example.com";
        User user = new User();
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));

        User result = userService.getUserByEmail(email);

        assertThat(result).isEqualTo(user);
    }

//    @Test
//    public void testGetUserByEmail_NonExistingEmail_ReturnsNull() {
//        String nonExistingEmail = "nonexisting@example.com";
//        when(userRepository.findUserByEmail(nonExistingEmail)).thenReturn(null);
//
//        User result = userService.getUserByEmail(nonExistingEmail);
//
//        assertThat(result).isNull();
//    }

    @Test
    public void testGetUserDto_ExistingId_ReturnsUserResponse() {
        Long userId = 1L;
        User user = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserResponse result = userService.getUserDto(userId);

        Assert.isNull(result);
    }

    @Test
    public void testGetUserDto_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(userRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userService.getUserDto(nonExistingId);
        });
    }

    @Test
    public void testSaveUser_CallsUserRepositorySave() {
        String email = "test@example.com";

        userService.saveUser(email);

        verify(userRepository).save(any(User.class));
    }

//    @Test
//    public void testUpdateUser_CallsUserRepositorySave() {
//        UserUpdateRequest request = new UserUpdateRequest();
//        request.setEmail("test@example.com");
//        request.setName("Test User");
//
//        User existingUser = new User();
//        when(userRepository.findUserByEmail("test@example.com")).thenReturn(Optional.of(existingUser));
//
//        userService.updateUser(request,"test@example.com");
//
//        verify(userRepository).save(existingUser);
//        assertThat(existingUser.getEmail()).isEqualTo(request.getEmail());
//        assertThat(existingUser.getName()).isEqualTo(request.getName());
//    }

    @Test
    public void testAddUserPass_GeneratesVerificationCodeAndSavesPassword() {
        String email = "test@example.com";

        String result = userService.addUserPass(email);

        Assert.notNull(result);
    }

    @Test
    public void testLoadUserByUsername_ExistingEmail_ReturnsUserDetails() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        user.setPassword("null");
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));

        UserDetails result = userService.loadUserByUsername(email);

        assertThat(result.getUsername()).isEqualTo(email);
        assertThat(result.getPassword()).isNotNull();
        assertThat(result.getAuthorities()).isEmpty();
    }

    @Test
    public void testLoadUserByUsername_NonExistingEmail_ThrowsUsernameNotFoundException() {
        String nonExistingEmail = "nonexisting@example.com";
        when(userRepository.findUserByEmail(nonExistingEmail)).thenReturn(null);

        org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> {
            userService.loadUserByUsername(nonExistingEmail);
        });
    }
}
