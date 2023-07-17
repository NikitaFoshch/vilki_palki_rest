package lab.space.vilki_palki_rest.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
public class UserUpdateRequest {
    @Size(max = 100, message = "Must be no more than {max} symbols")
    @Schema(name = "name", example = "Bobrito")
    private String name;
    @Size(max = 20, message = "Must be no more than {max} symbols")
    @Schema(name = "phone", example = "380974440088")
    private String phone;
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "UTC")
    @Schema(name = "birthday", example = "11.12.2000")
    private Instant birthday;
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
