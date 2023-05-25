package lab.space.vilki_palki_rest.model.promotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import lab.space.vilki_palki_rest.model.product.ProductResponse;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record PromotionResponse(
        Long id,
        String name,
        Long percent,
        Long totalPrice,
        String image,
        ProductResponse product
) {
}
