package lab.space.vilki_palki_rest.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Data
public class UserUpdateRequest {
    @Size(max = 100, message = "Must be no more than {max} symbols")
    @Schema(name = "name", example = "Bobrito")
    private String name;
    @Size(max = 20, message = "Must be no more than {max} symbols")
    @Schema(name = "phone", example = "380974440088")
    private String phone;
    @Size(max = 20, message = "Must be no more than {max} symbols")
    @Schema(name = "facebookId", example = "@facebookId")
    private String facebookId;
    @Size(max = 100, message = "Must be no more than {max} symbols")
    @Schema(name = "email", example = "bober@gmail.com")
    private String email;
    @Size(max = 20, message = "Must be no more than {max} symbols")
    @Schema(name = "cardNumber", example = "4441115564825971")
    private String cardNumber;
    @Min(value = 0, message = "Must be positive")
    @Schema(name = "bonusPoints", example = "100")
    private int bonusPoints;
}
