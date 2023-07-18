package lab.space.vilki_palki_rest.model.shopping_cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki_rest.model.product.ProductResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ShoppingCartResponse{
        private ProductResponse product;
        private int count;
}
