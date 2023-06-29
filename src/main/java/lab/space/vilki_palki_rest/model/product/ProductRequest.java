package lab.space.vilki_palki_rest.model.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductRequest {
    @Schema(name = "productTypeId", example = "1")
    private Long productTypeId;
    @Schema(name = "productCategoryId", example = "1")
    private Long productCategoryId;
}
