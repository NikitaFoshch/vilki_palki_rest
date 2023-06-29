package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.User;
import lab.space.vilki_palki_rest.model.user.UserAuthRequest;
import lab.space.vilki_palki_rest.model.user.UserRequest;
import lab.space.vilki_palki_rest.service.AuthService;
import lab.space.vilki_palki_rest.service.JwtService;
import lab.space.vilki_palki_rest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static java.util.Collections.singletonMap;

@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final UserService userService;
    private final JavaMailSender mailSender;
    private final AuthenticationManager authenticationManager;

    @Async
    @Override
    public void sendCodeByUserByEmail(UserRequest request) {
        checkEmail(request);

        String verificationCode = userService.addUserPass(request.getEmail());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject("Подтверждение входа");
        message.setText("Код подтверждения: " + verificationCode);

        mailSender.send(message);
    }

    @Override
    public ResponseEntity<?> authentication(UserAuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userService.getUserByEmail(request.getEmail());
            return ResponseEntity.ok(jwtService.generateTokens(user));
        } catch (BadCredentialsException e) {
            log.warn("Bad try of authentication with " + request);
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(singletonMap("error", e));
        }
    }

    private void checkEmail(UserRequest request) {
        if (userService.getUserByEmail(request.getEmail()) == null) {
            userService.saveUser(request.getEmail());
        }
    }

}
