package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.ProductType;
import lab.space.vilki_palki_rest.model.product_type.ProductTypeResponse;

import java.util.List;

public interface ProductTypeService {
    ProductType getProductType(Long id);
    ProductTypeResponse getProductTypeDto(Long id);
    List<ProductTypeResponse> getAllProductType();
}
