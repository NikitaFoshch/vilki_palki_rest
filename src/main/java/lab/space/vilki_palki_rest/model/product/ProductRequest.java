package lab.space.vilki_palki_rest.model.product;

import lombok.Data;

@Data
public class ProductRequest {
    private Long productTypeId;
    private Long productCategoryId;
}
