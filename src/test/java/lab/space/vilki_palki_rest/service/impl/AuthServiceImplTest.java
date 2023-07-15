package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.User;
import lab.space.vilki_palki_rest.model.user.UserAuthRequest;
import lab.space.vilki_palki_rest.model.user.UserRequest;
import lab.space.vilki_palki_rest.service.JwtService;
import lab.space.vilki_palki_rest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.mail.internet.MimeMessage;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserService userService;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendCodeByUserByEmail_SuccessfullySendsEmail() {
        UserRequest request = new UserRequest();
        request.setEmail("test@example.com");

        when(userService.addUserPass(request.getEmail())).thenReturn("123456");

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(request.getEmail());
        expectedMessage.setSubject("Подтверждение входа");
        expectedMessage.setText("Код подтверждения: 123456");

        authService.sendCodeByUserByEmail(request);

        verify(mailSender, times(1)).send(expectedMessage);
    }

    @Test
    public void testAuthentication_ValidCredentials_ReturnsJwtTokens() {
        String email = "test@example.com";
        Long password = 123L;
        UserAuthRequest request = new UserAuthRequest();
        request.setEmail(email);
        request.setPassword(password);

        Authentication authentication = mock(Authentication.class);
        User user = new User();
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(userService.getUserByEmail(email)).thenReturn(user);

        ResponseEntity<?> result = authService.authentication(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userService, times(1)).getUserByEmail(email);
    }

    @Test
    public void testAuthentication_InvalidCredentials_ReturnsUnauthorized() {
        String email = "test@example.com";
        Long password = 123L;
        UserAuthRequest request = new UserAuthRequest();
        request.setEmail(email);
        request.setPassword(password);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(BadCredentialsException.class);

        ResponseEntity<?> result = authService.authentication(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(result.getBody()).isInstanceOf(Map.class);
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    public void testCheckEmail_UserDoesNotExist_CreatesNewUser() {
        UserRequest request = new UserRequest();
        request.setEmail("test@example.com");

        when(userService.getUserByEmail(request.getEmail())).thenReturn(null);

        authService.checkEmail(request);

        verify(userService, times(1)).saveUser(request.getEmail());
    }

    @Test
    public void testCheckEmail_UserExists_DoesNotCreateNewUser() {
        UserRequest request = new UserRequest();
        request.setEmail("test@example.com");

        when(userService.getUserByEmail(request.getEmail())).thenReturn(new User());

        authService.checkEmail(request);

        verify(userService, never()).saveUser(request.getEmail());
    }

}
