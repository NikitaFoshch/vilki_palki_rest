package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.Product;
import lab.space.vilki_palki_rest.service.ProductCategoryService;
import lab.space.vilki_palki_rest.service.ProductTypeService;
import lab.space.vilki_palki_rest.service.StructureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import lab.space.vilki_palki_rest.model.product.ProductResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductMapper {
    private final StructureService structureService;
    private final ProductTypeService productTypeService;
    private final ProductCategoryService productCategoryService;

    public ProductResponse toSimpleDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .image(
                        UriComponentsBuilder
                                .fromUri(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUri())
                                .path("/files/" + product.getImage()).toUriString()
                )
                .price(product.getPrice())
                .build();
    }

    public ProductResponse toDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .image(
                        UriComponentsBuilder
                                .fromUri(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUri())
                                .path("/files/" + product.getImage()).toUriString()
                )
                .price(product.getPrice())
                .productInfo(product.getProductInfo())
                .description(product.getDescription())
                .productCategory(productCategoryService.getProductCategoryDTO(product.getProductCategory().getId()))
                .productType(productTypeService.getProductTypeDto(product.getProductType().getId()))
                .structures(
                        product.getStructures()
                                .stream()
                                .map(structure -> structureService.getStructureDto(structure.getId())).collect(Collectors.toList())
                )
                .build();
    }


}
