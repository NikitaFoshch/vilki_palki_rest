package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.ProductType;
import lab.space.vilki_palki_rest.model.product_type.ProductTypeResponse;
import org.springframework.data.domain.Page;

public interface ProductTypeService {
    ProductType getProductType(Long id);
    ProductTypeResponse getProductTypeDto(Long id);
    Page<ProductTypeResponse> getAllProductType();
}
