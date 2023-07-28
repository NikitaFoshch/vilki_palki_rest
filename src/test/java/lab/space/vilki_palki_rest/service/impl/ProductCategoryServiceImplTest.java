package lab.space.vilki_palki_rest.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lab.space.vilki_palki_rest.service.impl.ProductCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import lab.space.vilki_palki_rest.entity.ProductCategory;
import lab.space.vilki_palki_rest.mapper.ProductCategoryMapper;
import lab.space.vilki_palki_rest.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki_rest.repository.ProductCategoryRepository;
import org.springframework.util.Assert;
@ExtendWith(MockitoExtension.class)
public class ProductCategoryServiceImplTest {

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;


    @Test
    public void testGetProductCategory_ExistingId_ReturnsProductCategory() {
        Long productCategoryId = 1L;
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(1L);
        when(productCategoryRepository.findById(productCategoryId)).thenReturn(Optional.of(productCategory));

        ProductCategory result = productCategoryService.getProductCategory(productCategoryId);

        assertThat(result).isEqualTo(productCategory);
    }

    @Test
    public void testGetProductCategory_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            productCategoryService.getProductCategory(nonExistingId);
        });
    }

    @Test
    public void testGetProductCategoryDTO_ExistingId_ReturnsProductCategoryResponse() {
        Long productCategoryId = 1L;
        ProductCategory productCategory = new ProductCategory();
        when(productCategoryRepository.findById(productCategoryId)).thenReturn(Optional.of(productCategory));
        ProductCategoryResponse expectedResponse = ProductCategoryMapper.toSimpleDto(productCategory);

        ProductCategoryResponse result = productCategoryService.getProductCategoryDTO(productCategoryId);

        assertThat(result).isEqualTo(expectedResponse);
    }

//    @Test
//    public void testGetAllProductCategoryOrderByCreateAt_ReturnsListOfProductCategoryResponses() {
//        List<ProductCategory> productCategories = new ArrayList<>();
//        productCategories.add(new ProductCategory());
//        productCategories.add(new ProductCategory());
//
//        List<ProductCategoryResponse> result = productCategoryService.getAllProductCategoryOrderByCreateAt();
//
//        Assert.notNull(result);
//    }
}
