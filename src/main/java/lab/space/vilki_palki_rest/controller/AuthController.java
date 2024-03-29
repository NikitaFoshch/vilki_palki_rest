package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.user.UserAuthRequest;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@Tag(name = "Authentication", description = "Operations related to Auth and Login")
public class AuthController {
    private final AuthService authService;

    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PostMapping("enter-email")
    public ResponseEntity<?> enterEmail(@Valid @RequestBody UserRequest request,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        authService.sendCodeByUserByEmail(request);
        return ResponseEntity.ok().build();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody UserAuthRequest request,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        return authService.authentication(request);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PostMapping("refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request,
                                          HttpServletResponse response) {
        return authService.refreshToken(request,response);
    }
}
