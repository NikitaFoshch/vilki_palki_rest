package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.Product;
import lab.space.vilki_palki_rest.mapper.ProductMapper;
import lab.space.vilki_palki_rest.model.product.ProductResponse;
import lab.space.vilki_palki_rest.repository.ProductRepository;
import lab.space.vilki_palki_rest.service.ProductCategoryService;
import lab.space.vilki_palki_rest.service.ProductTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductCategoryService productCategoryService;

    @Mock
    private ProductTypeService productTypeService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

//    @Test
//    public void testGetAllProduct_ReturnsListOfProductResponses() {
//        Long productTypeId = 1L;
//        Long productCategoryId = 2L;
//        ProductType productType = new ProductType().setName("sedg");
//        productType.setId(productTypeId);
//        ProductCategory productCategory = new ProductCategory().setName("sfdhsh").setImage("erherh");
//        productCategory.setId(2L);
//
//        List<Product> products = new ArrayList<>();
//        products.add(new Product().setProductType(productType).setProductCategory(productCategory));
//        products.add(new Product().setProductType(productType).setProductCategory(productCategory));
//
//        when(productRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(products);
//        when(productTypeService.getProductType(productTypeId)).thenReturn(productType);
//        when(productCategoryService.getProductCategory(productCategoryId)).thenReturn(productCategory);
//
//        List<ProductResponse> expectedResponses = products.stream()
//                .map(productMapper::toSimpleDto)
//                .collect(Collectors.toList());
//
//        // Act
//        List<ProductResponse> result = productService.getAllProduct(productTypeId, productCategoryId,10);
//
//        // Assert
//        assertThat(result).isEqualTo(expectedResponses);
//    }

    @Test
    public void testGetProduct_ExistingId_ReturnsProduct() {
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(productId);

        assertThat(result).isEqualTo(product);
    }

    @Test
    public void testGetProduct_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            productService.getProduct(nonExistingId);
        });
    }

    @Test
    public void testGetProductDto_ExistingId_ReturnsProductResponse() {
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        ProductResponse expectedResponse = ProductResponse.builder().build();

        when(productMapper.toDto(product)).thenReturn(expectedResponse);

        ProductResponse result = productService.getProductDto(productId);

        assertThat(result).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetProductSimpleDto_ExistingId_ReturnsProductResponse() {
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        ProductResponse expectedResponse = ProductResponse.builder().id(1L).build();

        when(productMapper.toSimpleDto(product)).thenReturn(expectedResponse);

        ProductResponse result = productService.getProductSimpleDto(productId);

        assertThat(result).isEqualTo(expectedResponse);
    }
}
