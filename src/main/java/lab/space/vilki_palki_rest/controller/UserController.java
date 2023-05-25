package lab.space.vilki_palki_rest.controller;

import jakarta.validation.Valid;
import lab.space.vilki_palki_rest.model.user.UserResponse;
import lab.space.vilki_palki_rest.model.user.UserUpdateRequest;
import lab.space.vilki_palki_rest.service.UserService;
import lab.space.vilki_palki_rest.util.ErrorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("get-user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserDto(id));
    }

    @PutMapping("update-user")
    private ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateRequest request,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        userService.updateUser(request);
        return ResponseEntity.ok().build();
    }
}
