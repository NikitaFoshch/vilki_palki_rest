package lab.space.vilki_palki_rest.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record UserUpdateRequest (
        @NotNull(message = "Must be specified")
        Long id,
        @Size(max = 100, message = "Must be no more than {max} symbols")
        String name,
        @Size(max = 20, message = "Must be no more than {max} symbols")
        String phone,
        @JsonFormat(pattern = "dd.MM.yyyy", timezone = "UTC")
        Instant birthday,
        @Size(max = 20, message = "Must be no more than {max} symbols")
        String facebookId,
        @Size(max = 100, message = "Must be no more than {max} symbols")
        String email,
        @Size(max = 20, message = "Must be no more than {max} symbols")
        String cardNumber,
        @Min(value = 0, message = "Must be positive")
        int bonusPoints
){
}
