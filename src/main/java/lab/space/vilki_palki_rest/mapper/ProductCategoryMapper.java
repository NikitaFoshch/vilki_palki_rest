package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.ProductCategory;
import lab.space.vilki_palki_rest.model.product_category.ProductCategoryResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public interface ProductCategoryMapper {
    static ProductCategoryResponse toSimpleDto(ProductCategory productCategory) {
        return ProductCategoryResponse.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .build();
    }

    static ProductCategoryResponse toDto(ProductCategory productCategory) {
        return ProductCategoryResponse.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .image(
                        "http://slj.avada-media-dev1.od.ua/Vilki_Palki_Admin/files/" + productCategory.getImage()
                )
                .build();
    }
}
