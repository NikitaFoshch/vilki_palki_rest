package lab.space.vilki_palki_rest.model.promotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import lab.space.vilki_palki_rest.model.product.ProductResponse;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PromotionResponse{
    private Long id;
    private String name;
    private Long percent;
    private Long totalPrice;
    private String image;
    private ProductResponse product;
}
