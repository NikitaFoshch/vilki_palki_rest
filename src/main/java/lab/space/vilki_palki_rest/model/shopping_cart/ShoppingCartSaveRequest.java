package lab.space.vilki_palki_rest.model.shopping_cart;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ShoppingCartSaveRequest (
        @Digits(integer = 2, fraction = 0, message = "1-99")
        @Min(value = 1, message = "Must be > 0")
        int count,
        @NotNull(message = "Must be specified")
        @Min(value = 1, message = "Must be > 0")
        Long userId,
        @NotNull(message = "Must be specified")
        @Min(value = 1, message = "Must be > 0")
        Long productId
){

}
