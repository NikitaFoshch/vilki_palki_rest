package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.user.UserResponse;
import lab.space.vilki_palki_rest.model.user.UserUpdateRequest;
import lab.space.vilki_palki_rest.service.UserService;
import lab.space.vilki_palki_rest.util.ErrorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("user")
@AllArgsConstructor
@Tag(name = "Users", description = "Operations related to users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get user")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized"),
            @ApiResponse(responseCode = "404",description = "Not found")
    })
    @GetMapping("get-user")
    public ResponseEntity<?> getUser() {
        try {
            UserResponse userResponse = userService.getCurrentUser();
            return ResponseEntity.ok(userResponse);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

    @Operation(summary = "Update user by request", description = "Enter your value")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized"),
            @ApiResponse(responseCode = "404",description = "Not found")
    })
    @PutMapping("update-user")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateRequest request,
                                        BindingResult bindingResult,Principal principal) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        try {
            userService.updateUser(request,principal.getName());
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with email " + principal.getName());
        }
    }
}
