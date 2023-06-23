package lab.space.vilki_palki_rest.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserRequest {
    @Size(max = 100, message = "Must be no more than {max} symbols")
    @Schema(name = "email", example = "bober@gmail.com")
    private String email;
}
