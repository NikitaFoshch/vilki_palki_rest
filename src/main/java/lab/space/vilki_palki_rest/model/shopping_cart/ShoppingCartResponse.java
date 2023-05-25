package lab.space.vilki_palki_rest.model.shopping_cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki_rest.model.product.ProductResponse;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ShoppingCartResponse(
        Long id,
        Long userId,
        ProductResponse product,
        int count
) {
}
