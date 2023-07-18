package lab.space.vilki_palki_rest.model.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

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
    @NotBlank(message = "Must be specified")
    @Size(max = 1000, message = "Must be no more than {max} symbols")
    @Schema(name = "address", example = "st. Bobra 97")
    private String address;
    @NotNull(message = "Must be specified")
    @Digits(integer = 9, fraction = 0, message = "1-999999999")
    @Min(value = 1, message = "Must be > 0")
    @Schema(name = "price", example = "1000")
    private BigDecimal price;
}
