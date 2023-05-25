package lab.space.vilki_palki_rest.model.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderSaveRequest(
        @NotBlank(message = "Must be specified")
        @Size(max = 30, message = "Must be no more than {max} symbols")
        String orderCode,
        @NotBlank(message = "Must be specified")
        @Size(max = 1000, message = "Must be no more than {max} symbols")
        String productsList,
        @JsonFormat(pattern = "hh:mm dd.MM.yyyy", timezone = "UTC")
        Instant date,
        @NotBlank(message = "Must be specified")
        @Size(max = 1000, message = "Must be no more than {max} symbols")
        String address,
        @Digits(integer = 9, fraction = 0, message = "1-999999999")
        @Min(value = 1, message = "Must be > 0")
        BigDecimal price,
        @NotNull(message = "Must be specified")
        Long userId
) {
}
