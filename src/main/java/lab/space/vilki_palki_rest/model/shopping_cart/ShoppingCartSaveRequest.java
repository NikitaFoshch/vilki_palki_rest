package lab.space.vilki_palki_rest.model.shopping_cart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class ShoppingCartSaveRequest{
        @Digits(integer = 2, fraction = 0, message = "1-99")
        @Min(value = 1, message = "Must be > 0")
        @Schema(name = "count", example = "777")
        private int count;
        @NotNull(message = "Must be specified")
        @Min(value = 1, message = "Must be > 0")
        @Schema(name = "productId", example = "777")
        private Long productId;
}
