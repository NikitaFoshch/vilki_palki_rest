package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.model.user.UserAuthRequest;
import lab.space.vilki_palki_rest.model.user.UserRequest;
import lab.space.vilki_palki_rest.service.AuthService;
import lab.space.vilki_palki_rest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JavaMailSender mailSender;

    @Async
    @Override
    public void sendCodeByUserByEmail(UserRequest request) {
        checkEmail(request);

        String verificationCode = generateVerificationCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.email());
        message.setSubject("Подтверждение входа");
        message.setText("Код подтверждения: " + verificationCode);

        userService.addUserPass(request.email(), verificationCode);
        mailSender.send(message);

    }

    @Override
    public void authentication(UserAuthRequest request) {

    }

    private void checkEmail(UserRequest request) {
        if (userService.getUserByEmail(request.email()) == null) {
            userService.saveUser(request.email());
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        return String.valueOf(code);
    }
}
