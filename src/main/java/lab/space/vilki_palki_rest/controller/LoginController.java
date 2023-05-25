package lab.space.vilki_palki_rest.controller;

import jakarta.validation.Valid;
import lab.space.vilki_palki_rest.model.user.UserRequest;
import lab.space.vilki_palki_rest.service.AuthService;
import lab.space.vilki_palki_rest.util.ErrorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@AllArgsConstructor
public class LoginController {
    private final AuthService authService;

    @PostMapping("enter-email")
    public ResponseEntity<?> enterEmail(@Valid @RequestBody UserRequest request,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        authService.sendCodeByUserByEmail(request);
        return ResponseEntity.ok().build();
    }
}
