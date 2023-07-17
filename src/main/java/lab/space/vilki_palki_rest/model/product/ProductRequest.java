package lab.space.vilki_palki_rest.model.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ProductRequest {
    @Min(value = 0, message = "Must be positive")
    @Schema(name = "productTypeId", example = "0")
    private Long productTypeId;
    @Min(value = 1, message = "Must be positive")
    @Schema(name = "productCategoryId", example = "1")
    private Long productCategoryId;
}
