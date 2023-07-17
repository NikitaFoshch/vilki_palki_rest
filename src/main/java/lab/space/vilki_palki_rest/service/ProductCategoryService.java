package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.ProductCategory;
import lab.space.vilki_palki_rest.model.product_category.ProductCategoryResponse;
import org.springframework.data.domain.Page;

public interface ProductCategoryService {
    ProductCategory getProductCategory(Long id);

    ProductCategoryResponse getProductCategoryDTO(Long id);

    Page<ProductCategoryResponse> getAllProductCategoryOrderByCreateAt(int page);
}
