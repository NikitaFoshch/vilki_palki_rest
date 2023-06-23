package lab.space.vilki_palki_rest.model.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class OrderSaveRequest {
    @NotBlank(message = "Must be specified")
    @Size(max = 30, message = "Must be no more than {max} symbols")
    @Schema(name = "orderCode", example = "6574218678")
    private String orderCode;
    @NotBlank(message = "Must be specified")
    @Size(max = 1000, message = "Must be no more than {max} symbols")
    @Schema(name = "productsList", example = "Xleb, Pomidor, Banana")
    private String productsList;
    @JsonFormat(pattern = "hh:mm dd.MM.yyyy", timezone = "UTC")
    @Schema(name = "date", example = "12.12.2023")
    private Instant date;
    @NotBlank(message = "Must be specified")
    @Size(max = 1000, message = "Must be no more than {max} symbols")
    @Schema(name = "address", example = "st. Bobra 97")
    private String address;
    @Digits(integer = 9, fraction = 0, message = "1-999999999")
    @Min(value = 1, message = "Must be > 0")
    @Schema(name = "price", example = "1000")
    private BigDecimal price;
    @NotNull(message = "Must be specified")
    @Schema(name = "userId", example = "1")
    private Long userId;
}
