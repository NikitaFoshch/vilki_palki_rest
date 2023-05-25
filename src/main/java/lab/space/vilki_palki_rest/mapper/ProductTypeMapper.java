package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.ProductType;
import lab.space.vilki_palki_rest.model.product_type.ProductTypeResponse;

public interface ProductTypeMapper {
    static ProductTypeResponse toDto(ProductType productType) {
        return ProductTypeResponse.builder()
                .id(productType.getId())
                .name(productType.getName())
                .build();
    }
}
