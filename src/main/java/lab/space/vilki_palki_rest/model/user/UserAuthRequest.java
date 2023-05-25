package lab.space.vilki_palki_rest.model.user;

import jakarta.validation.constraints.Size;

public record UserAuthRequest (
        @Size(max = 100, message = "Must be no more than {max} symbols")
        String email,
        Long password
){
}
